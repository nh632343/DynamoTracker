package com.jinke.dynamodb.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.ScanResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.jinke.dynamodb.test.dao.impl.AccountDynamoDaoImpl;
import com.jinke.dynamodb.test.entity.AccountItem;
import com.jinke.dynamodb.test.entity.AccountItem2;
import com.jinke.dynamodb.test.entity.TestOpenId;
import com.jinke.dynamodb.test.entity.json.WriteDataLog;
import com.jinke.dynamodb.test.utils.ArrayUtils;
import com.jinke.dynamodb.test.utils.ByteBufferSerializer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TestWorker implements Runnable {
    public volatile static TestWorker singleTon;

    private static Logger writeLogger = LoggerFactory.getLogger(TestWorker.class.getName() + ".write");
    private static Logger lastKeyLogger = LoggerFactory.getLogger(TestWorker.class.getName() + ".lastkey");

    @Value("${logging.path}")
    private String logPath;

    AccountDynamoDaoImpl accountDynamoDao = new AccountDynamoDaoImpl();
    AccountDynamoDaoImpl writeDao = new AccountDynamoDaoImpl();

    private ScanResultPage<AccountItem> scanResultPage = new ScanResultPage<>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private long page = 0;
    private long count = 0;

    private List<Long> timeList = new ArrayList<>();
    private List<Integer> countList = new ArrayList<>();


    //lock
    private volatile List<WriteDataLog> writeDataLogList = new ArrayList<>();

    @Override
    public void run() {
//        long start = System.currentTimeMillis();
//        for (int j = 0; j < 1250; ++j) {
//            List data = new ArrayList();
//
//            for (int i = 0; i < 24; ++i) {
//                TestOpenId testOpenId = new TestOpenId();
//                testOpenId.setOpenId(String.valueOf(i) + "xxxxxxxxxx" + String.valueOf(j));
//                testOpenId.setAccountType(123);
//                testOpenId.setRegisterTime(25433215);
//                data.add(testOpenId);
//            }
//            long begin = System.currentTimeMillis();
//            accountDynamoDao.batchSave(data);
//            countList.add((int) (System.currentTimeMillis() - begin));
//            //timeList.add(System.currentTimeMillis());
//        }
//        long finish = System.currentTimeMillis();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
//        log.info("start :" + format.format(new Date(start)));
//        for (int i = 0, fin = countList.size(); i < fin; ++i) {
//            log.info("  useTime:" + countList.get(i));
//        }
//        log.info("finish:" + format.format(new Date(finish)));
//
//        if (true) return;

        Map<String, AttributeValue> key = JSON.parseObject("{\"account_name\":{\"s\":\"opposingle#379748420\"}}",
                new TypeReference<Map<String, AttributeValue>>(String.class, AttributeValue.class) {});
        scanResultPage.setLastEvaluatedKey(key);

        String resMsg = "";

        long beginTime = System.currentTimeMillis();
        boolean flag = true;
        while(flag) {
            Map<String, AttributeValue> lastLastKey = scanResultPage.getLastEvaluatedKey();
            timeList.add(System.currentTimeMillis());
            scanResultPage = accountDynamoDao.scanPage(AccountItem.class, scanResultPage.getLastEvaluatedKey());

            countList.add(scanResultPage == null || scanResultPage.getResults() == null ? 0 : scanResultPage.getResults().size());
            System.out.println("scan count " + (scanResultPage == null || scanResultPage.getResults() == null ? 0 : scanResultPage.getResults().size()));
            if (scanResultPage == null) {
                //fail
                resMsg = "error occur";
                saveLastKey(lastLastKey);
                flag = false;
                break;
            }
            ++page;
            count += scanResultPage.getResults() == null ? 0 : scanResultPage.getResults().size();

            flag = handleResult(scanResultPage.getResults(), page);
            if (!flag) {
                resMsg = "handleResult error";
                saveLastKey(lastLastKey);
                break;
            }

            if (count > 300000 || scanResultPage.getLastEvaluatedKey() == null || ArrayUtils.isEmpty(scanResultPage.getResults())) {
                //scan finish
                resMsg = "scan finish";
                flag = false;
                saveLastKey(scanResultPage.getLastEvaluatedKey());
                break;
            }
        }
        long endTime = System.currentTimeMillis();


        try {
            executorService.shutdown();
            executorService.awaitTermination(3, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

        log.info("time:" + System.currentTimeMillis() + "  thread pool is terminate, finish all write task");


        // scan data log


        log.info("beginTime:" + beginTime);
        for (int i = 0, fin = timeList.size(); i < fin; ++i) {
            log.info("time:" + timeList.get(i) + "  count:" + countList.get(i), "  page" + (i+1));
        }
        log.info("finish time:" + endTime);
        log.info(resMsg);
        log.info("page:" + page + " total count:" + count);


        //write data log
        for (WriteDataLog writeDataLog : writeDataLogList) {
            writeLogger.info("page:" + writeDataLog.page + " finish_batch_save_time:" + writeDataLog.end);
        }
    }

    private boolean handleResult(List<AccountItem> result, long page) {
        if (ArrayUtils.isEmpty(result)) return true;
        //executorService.execute(new HandleResultWorker(executorService, result, page, writeDao));

        List<AccountItem2> toWrite = new ArrayList<>();
        for (AccountItem accountItem : result) {
            AccountItem2 accountItem2 = getAccountItem2(accountItem);
            toWrite.add(accountItem2);
        }

        int totalSize = toWrite.size();
        int curIndex = 0;
        do {
            int last = curIndex + 24;
            if (last > totalSize) last = totalSize;

            try {
                executorService.execute(new WriteTestWorker(
                        writeDao, toWrite.subList(curIndex, last), page)
                );
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            curIndex = last;
        } while (curIndex < totalSize);

        return true;
    }

    private AccountItem2 getAccountItem2(AccountItem accountItem) {
        AccountItem2 accountItem2 = new AccountItem2();
        accountItem2.setAccountName(accountItem.getAccountName());
        accountItem2.setOpenId(accountItem.getOpenId());
        accountItem2.setAccountType(accountItem.getAccountType());
        accountItem2.setMobile(accountItem.getMobile());
        accountItem2.setPwd(accountItem.getPwd());
        accountItem2.setRegisterTime(accountItem.getRegisterTime());
        accountItem2.setPlamformId(accountItem.getPlamformId());
        accountItem2.setChannelId(accountItem.getChannelId());
        accountItem2.appId1RegisterTime = accountItem.appId1RegisterTime;
        accountItem2.appId2RegisterTime = accountItem.appId2RegisterTime;
        accountItem2.appId3RegisterTime = accountItem.appId3RegisterTime;
        accountItem2.appId4RegisterTime = accountItem.appId4RegisterTime;
        accountItem2.appId5RegisterTime = accountItem.appId5RegisterTime;
        accountItem2.appId6RegisterTime = accountItem.appId6RegisterTime;
        accountItem2.appId7RegisterTime = accountItem.appId7RegisterTime;
        accountItem2.appId8RegisterTime = accountItem.appId8RegisterTime;
        accountItem2.appId9RegisterTime = accountItem.appId9RegisterTime;
        accountItem2.appId10RegisterTime = accountItem.appId10RegisterTime;
        accountItem2.appId11RegisterTime = accountItem.appId11RegisterTime;
        accountItem2.appId12RegisterTime = accountItem.appId12RegisterTime;
        accountItem2.appId13RegisterTime = accountItem.appId13RegisterTime;
        accountItem2.appId14RegisterTime = accountItem.appId14RegisterTime;
        accountItem2.appId15RegisterTime = accountItem.appId15RegisterTime;
        accountItem2.appId16RegisterTime = accountItem.appId16RegisterTime;
        accountItem2.appId17RegisterTime = accountItem.appId17RegisterTime;
        accountItem2.appId18RegisterTime = accountItem.appId18RegisterTime;
        accountItem2.appId19RegisterTime = accountItem.appId19RegisterTime;
        accountItem2.appId20RegisterTime = accountItem.appId20RegisterTime;
        accountItem2.appId21RegisterTime = accountItem.appId21RegisterTime;
        accountItem2.appId22RegisterTime = accountItem.appId22RegisterTime;
        accountItem2.appId23RegisterTime = accountItem.appId23RegisterTime;
        accountItem2.appId24RegisterTime = accountItem.appId24RegisterTime;
        accountItem2.appId25RegisterTime = accountItem.appId25RegisterTime;
        accountItem2.appId26RegisterTime = accountItem.appId26RegisterTime;
        accountItem2.appId27RegisterTime = accountItem.appId27RegisterTime;
        accountItem2.appId28RegisterTime = accountItem.appId28RegisterTime;
        accountItem2.appId29RegisterTime = accountItem.appId29RegisterTime;
        accountItem2.appId30RegisterTime = accountItem.appId30RegisterTime;
        accountItem2.appId31RegisterTime = accountItem.appId31RegisterTime;
        accountItem2.appId32RegisterTime = accountItem.appId32RegisterTime;
        accountItem2.appId33RegisterTime = accountItem.appId33RegisterTime;
        accountItem2.appId34RegisterTime = accountItem.appId34RegisterTime;
        accountItem2.appId35RegisterTime = accountItem.appId35RegisterTime;
        accountItem2.appId36RegisterTime = accountItem.appId36RegisterTime;
        accountItem2.appId37RegisterTime = accountItem.appId37RegisterTime;
        accountItem2.appId38RegisterTime = accountItem.appId38RegisterTime;
        accountItem2.appId39RegisterTime = accountItem.appId39RegisterTime;
        accountItem2.appId40RegisterTime = accountItem.appId40RegisterTime;
        accountItem2.appId41RegisterTime = accountItem.appId41RegisterTime;
        accountItem2.appId42RegisterTime = accountItem.appId42RegisterTime;
        accountItem2.appId43RegisterTime = accountItem.appId43RegisterTime;
        accountItem2.appId44RegisterTime = accountItem.appId44RegisterTime;
        accountItem2.appId45RegisterTime = accountItem.appId45RegisterTime;
        accountItem2.appId46RegisterTime = accountItem.appId46RegisterTime;
        return accountItem2;
    }



    synchronized void addWriteData(WriteDataLog writeDataLog) {
        writeDataLogList.add(writeDataLog);
    }

    private void saveLastKey(Map<String, AttributeValue> lastKey) {
        if (lastKey == null) {
            lastKeyLogger.info("null");
            return;
        }
        ByteBufferSerializer.initConfig();
        lastKeyLogger.info(JSON.toJSONString(lastKey, SerializeConfig.getGlobalInstance()));
    }

    public String getThreadPoolMsg() {
        return executorService.toString();
    }

    private static Map<String, AttributeValue> restore(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String s = reader.readLine();
            return JSON.parseObject(s, new TypeReference<Map<String, AttributeValue>>(String.class, AttributeValue.class) {});
        } catch (Exception e) {
            e.printStackTrace();
            log.error("restore last key fail:", e);
        }
        return null;
    }
}
