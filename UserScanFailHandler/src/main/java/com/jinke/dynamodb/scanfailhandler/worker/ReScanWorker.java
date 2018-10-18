package com.jinke.dynamodb.scanfailhandler.worker;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.jinke.basecommon.dao.impl.AccountDynamoDaoImpl;
import com.jinke.basecommon.deliver.WorkerDeliver;
import com.jinke.basecommon.entity.AccountItem;
import com.jinke.basecommon.entity.json.Config;
import com.jinke.basecommon.utils.ArrayUtils;
import com.jinke.basecommon.utils.ByteBufferSerializer;
import com.jinke.basecommon.utils.FileUtils;
import com.jinke.basecommon.utils.ThreadPoolUtils;

import com.jinke.basecommon.worker.AccountItemResultWorker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

@Slf4j
@Component
public class ReScanWorker implements Runnable {
    private static Logger saveLastKeyLogger = LoggerFactory.getLogger(ReScanWorker.class.getName() + ".saveLastKey");


    @Value("${logging.path}")
    String logPath;

    @Autowired
    AccountDynamoDaoImpl accountDynamoDao;

    private ScanResultPage<AccountItem> scanResultPage = new ScanResultPage<>();

    private int page = 0;
    private int count = 0;

    private boolean runFlag = true;


    @Override
    public void run() {
        ByteBufferSerializer.initConfig();

        File lastKeyFile = new File(logPath, "worker" + File.separator + "lastKey.log");
        if(!lastKeyFile.exists() || !lastKeyFile.isFile()) {
            log.error("can not find " + lastKeyFile.getPath());
            return;
        }
        Map<String, AttributeValue> key = null;
        BufferedReader reader = null;
        //read last key from file
        try {
            reader = new BufferedReader(new FileReader(lastKeyFile));
            String json = reader.readLine();
            if (json == null || json.isEmpty()) return;

            key = JSON.parseObject(json, new TypeReference<Map<String, AttributeValue>>(String.class, AttributeValue.class) {});
        } catch (Exception e) {
            e.printStackTrace();
            log.error("restore last key fail: " , e);
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (Exception e) {e.printStackTrace();}
        }

        if (key == null) {
            log.error("key is null, exit");
            return;
        }
        scanResultPage.setLastEvaluatedKey(key);

        if(!FileUtils.deleteQuietly(lastKeyFile)) {
            log.error("delete lastkey file fail:" + lastKeyFile.getPath());
        }

        while (runFlag) {
            long begin = System.currentTimeMillis();
            log.info("scanPage begin :" + begin);
            scanAccountTableData();
            long end = System.currentTimeMillis();
            int scanCount = scanResultPage == null || scanResultPage.getResults() == null ? 0 : scanResultPage.getResults().size();
            log.info("scanCount:" + scanCount + " scanPage end :" + end + " usedTime:" + (end - begin));
            log.info("currentPage:" + page + "   currentCount:" + count);

            handleScanResult();

            if (scanResultPage == null || scanResultPage.getLastEvaluatedKey() == null) {
                log.info("scan finish");
                runFlag = false;
                break;
            }

            try {
                Thread.sleep(Config.getInstance().userScanInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //scan finish, wait for threadPool to finish all tasks
        WorkerDeliver.shutdown();
        log.info("all write tasks finish");
    }


    private void scanAccountTableData() {
        Map<String, AttributeValue> lastKey = scanResultPage == null ? null : scanResultPage.getLastEvaluatedKey();

        scanResultPage = scanPage(scanResultPage.getLastEvaluatedKey());

        if (scanResultPage == null ) { // fail
            log.error("scan page fail");
            runFlag = false;
            if (lastKey == null) return;
            saveLastKeyLogger.info(JSON.toJSONString(lastKey, SerializeConfig.getGlobalInstance()));
            return;
        }
        page++;
        count = count + scanResultPage.getResults().size();

        /*if (ArrayUtils.isEmpty(scanResultPage.getResults())) {
            log.info("scan finish");
            runFlag = false;

        } else {

            page++;
            count = count + scanResultPage.getResults().size();
        }*/

    }


    private void handleScanResult() {
        if (!runFlag || scanResultPage == null || ArrayUtils.isEmpty(scanResultPage.getResults())) {
            return;
        }

        //ThreadPoolUtils.get().execute(new AccountItemResultWorker(scanResultPage.getResults()));
        AccountItemResultWorker.handleAccountItems(scanResultPage.getResults());

    }


    private ScanResultPage<AccountItem> scanPage(Map<String, AttributeValue> exclusiveStartKey) {
        return accountDynamoDao.scanPage(AccountItem.class, exclusiveStartKey);
    }
}
