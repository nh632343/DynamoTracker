package com.jinke.basecommon.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

/**
 * 注　：not null代表数据库字段不能为空
 * 　　　null代表数据库字段为空
 */

@DynamoDBTable(tableName = "ikylin_rc_account")
public class AccountItem implements Serializable {

    //private static final long serialVersionUID = 8823025867674655664L;
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
     * 账户类型(1、匿名 2、手机 3、邮箱 4、自定义用户名)
     * 5: ‘google’, 6: ‘facebook’, 7: ‘twitter’,
     * 8:’opposingle’,9: ‘wan_ba’
     * (not null)
     */
    @DynamoDBAttribute(attributeName = "account_type")
    private Integer accountType;


    @DynamoDBAttribute(attributeName = "mobile")
    private String mobile;


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


    @DynamoDBAttribute(attributeName = "platform_id")
    private Integer plamformId;


    @DynamoDBAttribute(attributeName = "channel_id")
    private Integer channelId;


/*    public static long getSerialVersionUID() {
        return serialVersionUID;
    }*/

    public String getAccountName() {
        return accountName;
    }

    public AccountItem setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public AccountItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public AccountItem setAccountType(Integer accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AccountItem setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public AccountItem setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public AccountItem setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public Integer getPlamformId() {
        return plamformId;
    }

    public AccountItem setPlamformId(Integer plamformId) {
        this.plamformId = plamformId;
        return this;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public AccountItem setChannelId(Integer channelId) {
        this.channelId = channelId;
        return this;
    }

    @DynamoDBAttribute(attributeName = "app_id_1_register_time")
    public Integer appId1RegisterTime;


    @DynamoDBAttribute(attributeName = "app_id_2_register_time")
    public Integer appId2RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_3_register_time")
    public Integer appId3RegisterTime;


    @DynamoDBAttribute(attributeName = "app_id_4_register_time")
    public Integer appId4RegisterTime;



    @DynamoDBAttribute(attributeName = "app_id_5_register_time")
    public Integer appId5RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_6_register_time")
    public Integer appId6RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_7_register_time")
    public Integer appId7RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_8_register_time")
    public Integer appId8RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_9_register_time")
    public Integer appId9RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_10_register_time")
    public Integer appId10RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_11_register_time")
    public Integer appId11RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_12_register_time")
    public Integer appId12RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_13_register_time")
    public Integer appId13RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_14_register_time")
    public Integer appId14RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_15_register_time")
    public Integer appId15RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_16_register_time")
    public Integer appId16RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_17_register_time")
    public Integer appId17RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_18_register_time")
    public Integer appId18RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_19_register_time")
    public Integer appId19RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_20_register_time")
    public Integer appId20RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_21_register_time")
    public Integer appId21RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_22_register_time")
    public Integer appId22RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_23_register_time")
    public Integer appId23RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_24_register_time")
    public Integer appId24RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_25_register_time")
    public Integer appId25RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_26_register_time")
    public Integer appId26RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_27_register_time")
    public Integer appId27RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_28_register_time")
    public Integer appId28RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_29_register_time")
    public Integer appId29RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_30_register_time")
    public Integer appId30RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_31_register_time")
    public Integer appId31RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_32_register_time")
    public Integer appId32RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_33_register_time")
    public Integer appId33RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_34_register_time")
    public Integer appId34RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_35_register_time")
    public Integer appId35RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_36_register_time")
    public Integer appId36RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_37_register_time")
    public Integer appId37RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_38_register_time")
    public Integer appId38RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_39_register_time")
    public Integer appId39RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_40_register_time")
    public Integer appId40RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_41_register_time")
    public Integer appId41RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_42_register_time")
    public Integer appId42RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_43_register_time")
    public Integer appId43RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_44_register_time")
    public Integer appId44RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_45_register_time")
    public Integer appId45RegisterTime;

    @DynamoDBAttribute(attributeName = "app_id_46_register_time")
    public Integer appId46RegisterTime;

    public Integer getAppId1RegisterTime() {
        return appId1RegisterTime;
    }

    public void setAppId1RegisterTime(Integer appId1RegisterTime) {
        this.appId1RegisterTime = appId1RegisterTime;
    }

    public Integer getAppId2RegisterTime() {
        return appId2RegisterTime;
    }

    public void setAppId2RegisterTime(Integer appId2RegisterTime) {
        this.appId2RegisterTime = appId2RegisterTime;
    }

    public Integer getAppId3RegisterTime() {
        return appId3RegisterTime;
    }

    public void setAppId3RegisterTime(Integer appId3RegisterTime) {
        this.appId3RegisterTime = appId3RegisterTime;
    }

    public Integer getAppId4RegisterTime() {
        return appId4RegisterTime;
    }

    public void setAppId4RegisterTime(Integer appId4RegisterTime) {
        this.appId4RegisterTime = appId4RegisterTime;
    }

    public Integer getAppId5RegisterTime() {
        return appId5RegisterTime;
    }

    public void setAppId5RegisterTime(Integer appId5RegisterTime) {
        this.appId5RegisterTime = appId5RegisterTime;
    }

    public Integer getAppId6RegisterTime() {
        return appId6RegisterTime;
    }

    public void setAppId6RegisterTime(Integer appId6RegisterTime) {
        this.appId6RegisterTime = appId6RegisterTime;
    }

    public Integer getAppId7RegisterTime() {
        return appId7RegisterTime;
    }

    public void setAppId7RegisterTime(Integer appId7RegisterTime) {
        this.appId7RegisterTime = appId7RegisterTime;
    }

    public Integer getAppId8RegisterTime() {
        return appId8RegisterTime;
    }

    public void setAppId8RegisterTime(Integer appId8RegisterTime) {
        this.appId8RegisterTime = appId8RegisterTime;
    }

    public Integer getAppId9RegisterTime() {
        return appId9RegisterTime;
    }

    public void setAppId9RegisterTime(Integer appId9RegisterTime) {
        this.appId9RegisterTime = appId9RegisterTime;
    }

    public Integer getAppId10RegisterTime() {
        return appId10RegisterTime;
    }

    public void setAppId10RegisterTime(Integer appId10RegisterTime) {
        this.appId10RegisterTime = appId10RegisterTime;
    }

    public Integer getAppId11RegisterTime() {
        return appId11RegisterTime;
    }

    public void setAppId11RegisterTime(Integer appId11RegisterTime) {
        this.appId11RegisterTime = appId11RegisterTime;
    }

    public Integer getAppId12RegisterTime() {
        return appId12RegisterTime;
    }

    public void setAppId12RegisterTime(Integer appId12RegisterTime) {
        this.appId12RegisterTime = appId12RegisterTime;
    }

    public Integer getAppId13RegisterTime() {
        return appId13RegisterTime;
    }

    public void setAppId13RegisterTime(Integer appId13RegisterTime) {
        this.appId13RegisterTime = appId13RegisterTime;
    }

    public Integer getAppId14RegisterTime() {
        return appId14RegisterTime;
    }

    public void setAppId14RegisterTime(Integer appId14RegisterTime) {
        this.appId14RegisterTime = appId14RegisterTime;
    }

    public Integer getAppId15RegisterTime() {
        return appId15RegisterTime;
    }

    public void setAppId15RegisterTime(Integer appId15RegisterTime) {
        this.appId15RegisterTime = appId15RegisterTime;
    }

    public Integer getAppId16RegisterTime() {
        return appId16RegisterTime;
    }

    public void setAppId16RegisterTime(Integer appId16RegisterTime) {
        this.appId16RegisterTime = appId16RegisterTime;
    }

    public Integer getAppId17RegisterTime() {
        return appId17RegisterTime;
    }

    public void setAppId17RegisterTime(Integer appId17RegisterTime) {
        this.appId17RegisterTime = appId17RegisterTime;
    }

    public Integer getAppId18RegisterTime() {
        return appId18RegisterTime;
    }

    public void setAppId18RegisterTime(Integer appId18RegisterTime) {
        this.appId18RegisterTime = appId18RegisterTime;
    }

    public Integer getAppId19RegisterTime() {
        return appId19RegisterTime;
    }

    public void setAppId19RegisterTime(Integer appId19RegisterTime) {
        this.appId19RegisterTime = appId19RegisterTime;
    }

    public Integer getAppId20RegisterTime() {
        return appId20RegisterTime;
    }

    public void setAppId20RegisterTime(Integer appId20RegisterTime) {
        this.appId20RegisterTime = appId20RegisterTime;
    }

    public Integer getAppId21RegisterTime() {
        return appId21RegisterTime;
    }

    public void setAppId21RegisterTime(Integer appId21RegisterTime) {
        this.appId21RegisterTime = appId21RegisterTime;
    }

    public Integer getAppId22RegisterTime() {
        return appId22RegisterTime;
    }

    public void setAppId22RegisterTime(Integer appId22RegisterTime) {
        this.appId22RegisterTime = appId22RegisterTime;
    }

    public Integer getAppId23RegisterTime() {
        return appId23RegisterTime;
    }

    public void setAppId23RegisterTime(Integer appId23RegisterTime) {
        this.appId23RegisterTime = appId23RegisterTime;
    }

    public Integer getAppId24RegisterTime() {
        return appId24RegisterTime;
    }

    public void setAppId24RegisterTime(Integer appId24RegisterTime) {
        this.appId24RegisterTime = appId24RegisterTime;
    }

    public Integer getAppId25RegisterTime() {
        return appId25RegisterTime;
    }

    public void setAppId25RegisterTime(Integer appId25RegisterTime) {
        this.appId25RegisterTime = appId25RegisterTime;
    }

    public Integer getAppId26RegisterTime() {
        return appId26RegisterTime;
    }

    public void setAppId26RegisterTime(Integer appId26RegisterTime) {
        this.appId26RegisterTime = appId26RegisterTime;
    }

    public Integer getAppId27RegisterTime() {
        return appId27RegisterTime;
    }

    public void setAppId27RegisterTime(Integer appId27RegisterTime) {
        this.appId27RegisterTime = appId27RegisterTime;
    }

    public Integer getAppId28RegisterTime() {
        return appId28RegisterTime;
    }

    public void setAppId28RegisterTime(Integer appId28RegisterTime) {
        this.appId28RegisterTime = appId28RegisterTime;
    }

    public Integer getAppId29RegisterTime() {
        return appId29RegisterTime;
    }

    public void setAppId29RegisterTime(Integer appId29RegisterTime) {
        this.appId29RegisterTime = appId29RegisterTime;
    }

    public Integer getAppId30RegisterTime() {
        return appId30RegisterTime;
    }

    public void setAppId30RegisterTime(Integer appId30RegisterTime) {
        this.appId30RegisterTime = appId30RegisterTime;
    }

    public Integer getAppId31RegisterTime() {
        return appId31RegisterTime;
    }

    public void setAppId31RegisterTime(Integer appId31RegisterTime) {
        this.appId31RegisterTime = appId31RegisterTime;
    }

    public Integer getAppId32RegisterTime() {
        return appId32RegisterTime;
    }

    public void setAppId32RegisterTime(Integer appId32RegisterTime) {
        this.appId32RegisterTime = appId32RegisterTime;
    }

    public Integer getAppId33RegisterTime() {
        return appId33RegisterTime;
    }

    public void setAppId33RegisterTime(Integer appId33RegisterTime) {
        this.appId33RegisterTime = appId33RegisterTime;
    }

    public Integer getAppId34RegisterTime() {
        return appId34RegisterTime;
    }

    public void setAppId34RegisterTime(Integer appId34RegisterTime) {
        this.appId34RegisterTime = appId34RegisterTime;
    }

    public Integer getAppId35RegisterTime() {
        return appId35RegisterTime;
    }

    public void setAppId35RegisterTime(Integer appId35RegisterTime) {
        this.appId35RegisterTime = appId35RegisterTime;
    }

    public Integer getAppId36RegisterTime() {
        return appId36RegisterTime;
    }

    public void setAppId36RegisterTime(Integer appId36RegisterTime) {
        this.appId36RegisterTime = appId36RegisterTime;
    }

    public Integer getAppId37RegisterTime() {
        return appId37RegisterTime;
    }

    public void setAppId37RegisterTime(Integer appId37RegisterTime) {
        this.appId37RegisterTime = appId37RegisterTime;
    }

    public Integer getAppId38RegisterTime() {
        return appId38RegisterTime;
    }

    public void setAppId38RegisterTime(Integer appId38RegisterTime) {
        this.appId38RegisterTime = appId38RegisterTime;
    }

    public Integer getAppId39RegisterTime() {
        return appId39RegisterTime;
    }

    public void setAppId39RegisterTime(Integer appId39RegisterTime) {
        this.appId39RegisterTime = appId39RegisterTime;
    }

    public Integer getAppId40RegisterTime() {
        return appId40RegisterTime;
    }

    public void setAppId40RegisterTime(Integer appId40RegisterTime) {
        this.appId40RegisterTime = appId40RegisterTime;
    }

    public Integer getAppId41RegisterTime() {
        return appId41RegisterTime;
    }

    public void setAppId41RegisterTime(Integer appId41RegisterTime) {
        this.appId41RegisterTime = appId41RegisterTime;
    }

    public Integer getAppId42RegisterTime() {
        return appId42RegisterTime;
    }

    public void setAppId42RegisterTime(Integer appId42RegisterTime) {
        this.appId42RegisterTime = appId42RegisterTime;
    }

    public Integer getAppId43RegisterTime() {
        return appId43RegisterTime;
    }

    public void setAppId43RegisterTime(Integer appId43RegisterTime) {
        this.appId43RegisterTime = appId43RegisterTime;
    }

    public Integer getAppId44RegisterTime() {
        return appId44RegisterTime;
    }

    public void setAppId44RegisterTime(Integer appId44RegisterTime) {
        this.appId44RegisterTime = appId44RegisterTime;
    }

    public Integer getAppId45RegisterTime() {
        return appId45RegisterTime;
    }

    public void setAppId45RegisterTime(Integer appId45RegisterTime) {
        this.appId45RegisterTime = appId45RegisterTime;
    }

    public Integer getAppId46RegisterTime() {
        return appId46RegisterTime;
    }

    public void setAppId46RegisterTime(Integer appId46RegisterTime) {
        this.appId46RegisterTime = appId46RegisterTime;
    }
}
