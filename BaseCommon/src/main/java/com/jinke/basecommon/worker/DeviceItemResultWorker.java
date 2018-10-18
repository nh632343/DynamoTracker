package com.jinke.basecommon.worker;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.jinke.basecommon.dao.impl.DeviceDynamoDaoImpl;
import com.jinke.basecommon.deliver.DeviceDeliver;
import com.jinke.basecommon.entity.AnomymousAccountItem;
import com.jinke.basecommon.entity.DeviceItem;
import com.jinke.basecommon.utils.AccountUtils;
import com.jinke.basecommon.utils.ArrayUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DeviceItemResultWorker implements Runnable {
    private static Logger queryFailLogger = LoggerFactory.getLogger(DeviceItemResultWorker.class.getName() + ".queryfail");

    List<AnomymousAccountItem> anomymousAccountItems;
    DeviceDynamoDaoImpl deviceDynamoDao;

    public DeviceItemResultWorker(List<AnomymousAccountItem> anomymousAccountItems, DeviceDynamoDaoImpl deviceDynamoDao) {
        this.anomymousAccountItems = anomymousAccountItems;
        this.deviceDynamoDao = deviceDynamoDao;
    }


    @Override
    public void run() {
         if (ArrayUtils.isEmpty(anomymousAccountItems)) return;
        List<DeviceItem> toWriteList = new ArrayList<DeviceItem>();

         for (AnomymousAccountItem anomymousAccountItem : anomymousAccountItems) {
             AttributeValue attributeValue = new AttributeValue().withS(anomymousAccountItem.getOpenId());
             Map<String, AttributeValue> valueMap = new HashMap<String, AttributeValue>();
             valueMap.put(":v_id", attributeValue);
             DynamoDBQueryExpression<DeviceItem> expression = new DynamoDBQueryExpression<DeviceItem>()
                     .withConsistentRead(false)
                     .withIndexName("open_id-index")
                     .withKeyConditionExpression("open_id = :v_id")
                     .withExpressionAttributeValues(valueMap);

             long begin = System.currentTimeMillis();
             log.info("query begin: open_id:" + anomymousAccountItem.getOpenId() + "  anony_name:" + anomymousAccountItem.getAnonymousName());
             List<DeviceItem> result = deviceDynamoDao.query(DeviceItem.class, expression);
             long end = System.currentTimeMillis();
             log.info("query finish: open_id:" + anomymousAccountItem.getOpenId() + "  usedtime:" + (end-begin) + "  result:" + result);

             if (result == null) {  //query fail
                 queryFailLogger.info( JSON.toJSONString(anomymousAccountItem) );
                 continue;
             }
             if (ArrayUtils.isEmpty(result)) continue;
             String appIdRegisterTime = AccountUtils.getAppIdRegisterTime(anomymousAccountItem);
             for (DeviceItem deviceItem : result) {
                 deviceItem.setAppIdRegisterTime(appIdRegisterTime);
                 toWriteList.add(deviceItem);
             }
         }
         DeviceDeliver.deliver(toWriteList);
    }
}
