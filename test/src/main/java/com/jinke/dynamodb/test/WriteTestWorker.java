package com.jinke.dynamodb.test;

import com.jinke.dynamodb.test.dao.DynamoDao;
import com.jinke.dynamodb.test.entity.json.WriteDataLog;
import com.jinke.dynamodb.test.utils.ArrayUtils;

import java.util.List;

public class WriteTestWorker implements Runnable {
    DynamoDao dynamoDao;
    List data;
    long page;

    public WriteTestWorker(DynamoDao dynamoDao, List data, long page) {
        this.dynamoDao = dynamoDao;
        this.data = data;
        this.page= page;
    }

    @Override
    public void run() {
        if (ArrayUtils.isEmpty(data)) return;
        dynamoDao.batchSave(data);
        long end = System.currentTimeMillis();

        TestWorker.singleTon.addWriteData(new WriteDataLog(page, end));
    }
}
