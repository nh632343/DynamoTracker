package com.jinke.dynamodb.test.entity.json;

public class WriteDataLog {
    public final long page;

    public final long end;

    public WriteDataLog(long page, long endTime) {
        this.page = page;
        end = endTime;
    }
}
