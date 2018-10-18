package com.jinke.stream.kinesis;


import com.amazonaws.services.dynamodbv2.streamsadapter.model.RecordAdapter;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.model.Record;
import com.jinke.basecommon.entity.AccountItem;
import com.jinke.basecommon.utils.ThreadPoolUtils;
import com.jinke.stream.utils.ConvertUtils;
import com.jinke.stream.worker.ResultWorker;

import java.nio.charset.Charset;
import java.util.List;

public class StreamsRecordProcessor implements IRecordProcessor {

    private Integer checkpointCounter;




    @Override
    public void initialize(String shardId) {
        checkpointCounter = 0;
    }

    @Override
    public void processRecords(List<Record> records,
                               IRecordProcessorCheckpointer checkpointer) {
        for(Record record : records) {
            String data = new String(record.getData().array(), Charset.forName("UTF-8"));
            System.out.println(data);
            if(record instanceof RecordAdapter) {
                com.amazonaws.services.dynamodbv2.model.Record streamRecord = ((RecordAdapter) record).getInternalObject();


                switch(streamRecord.getEventName()) {
                    case "INSERT" : case "MODIFY" :
                        AccountItem accountItem = ConvertUtils.toAccountItem(streamRecord.getDynamodb().getNewImage());
                        ThreadPoolUtils.get().execute(new ResultWorker(accountItem));
                        break;
                    /*case "REMOVE" :
                        StreamsAdapterHelper.deleteItem(dynamoDBClient, tableName, streamRecord.getDynamodb().getKeys().get("Id").getN());*/
                }
            }
            checkpointCounter += 1;
            if(checkpointCounter % 10 == 0) {
                try {
                    checkpointer.checkpoint();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void shutdown(IRecordProcessorCheckpointer checkpointer,
                         ShutdownReason reason) {
        if(reason == ShutdownReason.TERMINATE) {
            try {
                checkpointer.checkpoint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
