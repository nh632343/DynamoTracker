package com.jinke.dynamodb.test.entity.json;

import com.alibaba.fastjson.annotation.JSONField;

public class PlatformInfo {
    @JSONField(name = "platform_id")
    private Integer platformId;

    @JSONField(name = "channel_id")
    private Integer channelId;

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }
}
