package com.jinke.basecommon.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

/**
 * 第三方账号表
 * 注　：not null代表数据库字段不能为空
 * 　　　null代表数据库字段为空
 */

@DynamoDBTable(tableName = "ikylin_rc_third_user")
public class ThirdUserItem implements Serializable {

    private static final long serialVersionUID = -8775121079392336550L;

    /**
     * 主键，用户名(not null)
     */
    @DynamoDBHashKey(attributeName = "platform_id_channel_id_open_id")
    private String accountTypeOpenId;

    /**
     * /**
     * 唯一标识(not null)
     */
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "open_id-index")
    @DynamoDBAttribute(attributeName = "open_id")
    private String openId;

    /**
     * 帐号对应的app_id注册时间，{appId:register_time,appId:register_time}
     */
    @DynamoDBAttribute(attributeName = "app_id_register_time")
    private String appIdRegisterTime;

    /**
     * 账户注册时间(not null)
     */
    @DynamoDBAttribute(attributeName = "register_time")
    private Long registerTime;


    @DynamoDBAttribute(attributeName = "platform_ info")
    private String platformInfo;

    public String getAccountTypeOpenId() {
        return accountTypeOpenId;
    }

    public ThirdUserItem setAccountTypeOpenId(String accountTypeOpenId) {
        this.accountTypeOpenId = accountTypeOpenId;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public ThirdUserItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAppIdRegisterTime() {
        return appIdRegisterTime;
    }

    public ThirdUserItem setAppIdRegisterTime(String appIdRegisterTime) {
        this.appIdRegisterTime = appIdRegisterTime;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public ThirdUserItem setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getPlatformInfo() {
        return platformInfo;
    }

    public ThirdUserItem setPlatformInfo(String platformInfo) {
        this.platformInfo = platformInfo;
        return this;
    }



}
