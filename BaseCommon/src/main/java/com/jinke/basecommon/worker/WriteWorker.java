package com.jinke.basecommon.worker;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.dao.DynamoDao;
import com.jinke.basecommon.utils.ArrayUtils;
import com.jinke.basecommon.utils.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.List;

@Slf4j
public class WriteWorker implements Runnable {
    private static Logger writeFailLogger = LoggerFactory.getLogger(WriteWorker.class.getName() + ".writefail");

    List objects;
    Supplier<DynamoDao> supplier;

    public WriteWorker(List objects, Supplier<DynamoDao> supplier) {
        this.objects = objects;
        this.supplier = supplier;
    }

    @Override
    public void run() {
        if (ArrayUtils.isEmpty(objects)) return;
        DynamoDao dynamoDao = supplier.get();
        long begin = System.currentTimeMillis();
        log.info("write begin:count:" + objects.size() + " dao:" + dynamoDao.getClass().getName() + " time:" + begin);
        if (dynamoDao.batchSave(objects)) {
            long end = System.currentTimeMillis();
            log.info("write end:count:" + objects.size() + " dao:" + dynamoDao.getClass().getName() + " time:" + end + " useTime:" + (end - begin));
        } else {
            MDC.put("entityName", objects.get(0).getClass().getName());
            writeFailLogger.info(JSON.toJSONString(objects));
            MDC.remove("entityName");
        }
    }
}
