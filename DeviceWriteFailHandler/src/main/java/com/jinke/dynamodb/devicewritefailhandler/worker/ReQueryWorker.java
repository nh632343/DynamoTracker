package com.jinke.dynamodb.devicewritefailhandler.worker;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.dao.impl.DeviceDynamoDaoImpl;
import com.jinke.basecommon.entity.AnomymousAccountItem;
import com.jinke.basecommon.worker.DeviceItemResultWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReQueryWorker implements Runnable {
    @Value("${logging.path}")
    String logPath;

    @Autowired
    DeviceDynamoDaoImpl deviceDynamoDao;

    @Override
    public void run() {
        File queryFailFile = new File(logPath, "query_fail/com.jinke.basecommon.entity.AnomymousAccountItem");
        if (!queryFailFile.exists() || !queryFailFile.isFile()) {
            log.error("file not found:" + queryFailFile.getPath());
            return;
        }

        List<AnomymousAccountItem> anomymousAccountItemList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(queryFailFile));
            String s;
            while ((s = reader.readLine()) != null) {
                AnomymousAccountItem anomymousAccountItem = JSON.parseObject(s, AnomymousAccountItem.class);
                if (anomymousAccountItem != null) anomymousAccountItemList.add(anomymousAccountItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:", e);
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (Exception e) {e.printStackTrace();}
        }

        queryFailFile.delete();

        new DeviceItemResultWorker(anomymousAccountItemList, deviceDynamoDao).run();
    }
}
