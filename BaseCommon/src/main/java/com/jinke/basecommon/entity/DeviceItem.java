package com.jinke.basecommon.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

/**
 * 注　：not null代表数据库字段不能为空
 * 　　　null代表数据库字段为空
 * 匿名用户设备表，用来绑定设备对应open_id
 */

@DynamoDBTable(tableName = "ikylin_rc_anonymous_device")
public class DeviceItem implements Serializable {

    private static final long serialVersionUID = 348211587479477866L;

    /**
     * 主键，用户名(not null)
     */
    @DynamoDBHashKey(attributeName = "option_name_option_var")
    private String optionNameOptionVar;

    /**
     * /**
     * 唯一标识(not null)
     */
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "open_id-index")
    @DynamoDBAttribute(attributeName = "open_id")
    private String openId;

    /**
     * 账户注册时间(not null)
     */
    @DynamoDBAttribute(attributeName = "register_time")
    private Long registerTime;

    /**
     * 帐号对应的app_id注册时间，{appId:register_time,appId:register_time}
     */
    @DynamoDBAttribute(attributeName = "app_id_register_time")
    private String appIdRegisterTime;

    public String getOptionNameOptionVar() {
        return optionNameOptionVar;
    }

    public DeviceItem setOptionNameOptionVar(String optionNameOptionVar) {
        this.optionNameOptionVar = optionNameOptionVar;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public DeviceItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public DeviceItem setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getAppIdRegisterTime() {
        return appIdRegisterTime;
    }

    public DeviceItem setAppIdRegisterTime(String appIdRegisterTime) {
        this.appIdRegisterTime = appIdRegisterTime;
        return this;
    }


}
