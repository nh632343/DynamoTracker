package com.jinke.basecommon.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;


/**
 * 用户详情表
 * 注　：not null代表数据库字段不能为空
 * 　　　null代表数据库字段为空
 */

@DynamoDBTable(tableName = "ikylin_rc_user_detail")
public class UserDetailItem implements Serializable {

    private static final long serialVersionUID = -1764019873864851374L;

    /**
     * /**
     * 主键,唯一标识(not null)
     */
    @DynamoDBHashKey(attributeName = "open_id")
    private String openId;

    /**
     * 账户类型(1、匿名 2、手机 3、邮箱 4、自定义用户名)
     * 5: ‘google’, 6: ‘facebook’, 7: ‘twitter’,
     * 8:’opposingle’,9: ‘wan_ba’
     * (not null)
     */
    @DynamoDBAttribute(attributeName = "account_type")
    private Integer accountType;

    /**
     * 账户注册时间(not null)
     */
    @DynamoDBAttribute(attributeName = "register_time")
    private Long registerTime;

    /**
     * 昵称(null)
     */
    @DynamoDBAttribute(attributeName = "jinke_nick_name")
    private String nickName;

    /**
     * 头像(null)
     */
    @DynamoDBAttribute(attributeName = "jinke_icon")
    private String icon;

    /**
     * 性别(null)
     */
    @DynamoDBAttribute(attributeName = "gender")
    private String gender;

    /**
     * 身份证(null)
     */
    @DynamoDBAttribute(attributeName = "identity_number")
    private String identityNumber;

    /**
     * 真实姓名(null)
     */
    @DynamoDBAttribute(attributeName = "identity_name")
    private String identityName;

    public String getOpenId() {
        return openId;
    }

    public UserDetailItem setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public UserDetailItem setAccountType(Integer accountType) {
        this.accountType = accountType;
        return this;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public UserDetailItem setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
        return this;
    }

    public String getNickName() {
        return nickName;
    }

    public UserDetailItem setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public UserDetailItem setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserDetailItem setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public UserDetailItem setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
        return this;
    }

    public String getIdentityName() {
        return identityName;
    }

    public UserDetailItem setIdentityName(String identityName) {
        this.identityName = identityName;
        return this;
    }


}
