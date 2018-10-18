package com.jinke.dynamodb.test.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

/**
 * 注　：not null代表数据库字段不能为空
 * 　　　null代表数据库字段为空
 */

@DynamoDBTable(tableName = "ikylin_rc_user")
public class UserItem implements Serializable {

    private static final long serialVersionUID = 8823025867674655664L;
    /**
     * 主键，用户名(not null)
     */
    @DynamoDBHashKey(attributeName = "account_name")
    private String accountName;

    /**
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

    /**
     * 密码(null)
     */
    @DynamoDBAttribute(attributeName = "password")
    private String pwd;
    public String getAccountName() {
        return accountName;
    }

    public UserItem setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public UserItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getAppIdRegisterTime() {
        return appIdRegisterTime;
    }

    public UserItem setAppIdRegisterTime(String appIdRegisterTime) {
        this.appIdRegisterTime = appIdRegisterTime;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public UserItem setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public UserItem setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }


}
