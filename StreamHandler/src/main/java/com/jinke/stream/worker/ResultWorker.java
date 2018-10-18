package com.jinke.stream.worker;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.dao.DynamoDao;
import com.jinke.basecommon.entity.AccountItem;
import com.jinke.basecommon.entity.ThirdUserItem;
import com.jinke.basecommon.entity.UserDetailItem;
import com.jinke.basecommon.entity.UserItem;
import com.jinke.basecommon.entity.json.AppIdRegisterTime;
import com.jinke.basecommon.entity.json.PlatformInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ResultWorker implements Runnable {
    private static Logger writeFailLogger = LoggerFactory.getLogger(ResultWorker.class.getName() + ".writefail");

    AccountItem mItem;

    public ResultWorker(AccountItem item) {
        mItem = item;

    }

    @Override
    public void run() {
        AccountItem accountItem = mItem;

            int accountType = accountItem.getAccountType();
            if (accountType == 1) return;
            if (accountType > 4) {
                ThirdUserItem thirdUserItem = new ThirdUserItem();
                thirdUserItem.setAccountTypeOpenId(accountType + "#" + accountItem.getOpenId());
                thirdUserItem.setOpenId(accountItem.getOpenId());
                thirdUserItem.setRegisterTime(accountItem.getRegisterTime());

                PlatformInfo platformInfo = new PlatformInfo();
                platformInfo.setChannelId(accountItem.getChannelId());
                platformInfo.setPlatformId(accountItem.getPlamformId());
                thirdUserItem.setPlatformInfo(JSON.toJSONString(platformInfo));

                thirdUserItem.setAppIdRegisterTime(getAppIdRegisterTime(accountItem));

                save(DaoContainer.getThirdUserDynamoDao(), thirdUserItem);

            } else {  // accountType is 2 or 3 or 4
                UserItem userItem = new UserItem();
                userItem.setAccountName(getAccountTypeString(accountType) + "#" + accountItem.getAccountName());
                userItem.setPwd(accountItem.getPwd());
                userItem.setOpenId(accountItem.getOpenId());
                userItem.setRegisterTime(accountItem.getRegisterTime());
                userItem.setAppIdRegisterTime(getAppIdRegisterTime(accountItem));
                save(DaoContainer.getUserDynamoDao(), userItem);

                UserDetailItem userDetailItem = new UserDetailItem();
                userDetailItem.setOpenId(accountItem.getOpenId());
                userDetailItem.setAccountType(accountType);
                userDetailItem.setRegisterTime(accountItem.getRegisterTime());
                save(DaoContainer.getUserDetailDynamoDao(), userDetailItem);

                //如果是账号绑有手机，再增加一条记录
                if (accountType == 4 && accountItem.getMobile() != null && !accountItem.getMobile().isEmpty()) {
                    UserItem mobileUserItem = new UserItem();
                    mobileUserItem.setAccountName(getAccountTypeString(2) + "#" + accountItem.getMobile());
                    mobileUserItem.setPwd(userItem.getPwd());
                    mobileUserItem.setOpenId(userItem.getOpenId());
                    mobileUserItem.setRegisterTime(userItem.getRegisterTime());
                    mobileUserItem.setAppIdRegisterTime(userItem.getAppIdRegisterTime());
                    save(DaoContainer.getUserDynamoDao(), mobileUserItem);
                }
            }

    }

    private static void save(DynamoDao dynamoDao, Object object) {
        long begin = System.currentTimeMillis();
        log.info("write stream begin:" + " dao:" + dynamoDao.getClass().getName() + " time:" + begin);
        if (dynamoDao.save(object)) {
            long end = System.currentTimeMillis();
            log.info("write stream end:" + " dao:" + dynamoDao.getClass().getName() + " time:" + end + " useTime:" + (end - begin));
        } else {
            List tmp = new ArrayList();
            tmp.add(object);
            MDC.put("entityName", object.getClass().getName());
            writeFailLogger.info(JSON.toJSONString(tmp));
        }
    }


    /**
     *
     * @param accountType 只能是2到4
     * @return
     */
    private static String getAccountTypeString(int accountType) {
        switch (accountType) {
            case 2: return "mobile";
            case 3: return "email";
            case 4: return "account_name";
        }
        return "";
    }

    /**
     *
     * @param accountItem
     * @return
     */
    private static String getAppIdRegisterTime(AccountItem accountItem) {
        AppIdRegisterTime appIdRegisterTime = new AppIdRegisterTime();

        appIdRegisterTime.appId1 = accountItem.appId1RegisterTime;

        appIdRegisterTime.appId2 = accountItem.appId2RegisterTime;

        appIdRegisterTime.appId3 = accountItem.appId3RegisterTime;

        appIdRegisterTime.appId4 = accountItem.appId4RegisterTime;

        appIdRegisterTime.appId5 = accountItem.appId5RegisterTime;

        appIdRegisterTime.appId6 = accountItem.appId6RegisterTime;

        appIdRegisterTime.appId7 = accountItem.appId7RegisterTime;

        appIdRegisterTime.appId8 = accountItem.appId8RegisterTime;

        appIdRegisterTime.appId9 = accountItem.appId9RegisterTime;

        appIdRegisterTime.appId10 = accountItem.appId10RegisterTime;

        appIdRegisterTime.appId11 = accountItem.appId11RegisterTime;

        appIdRegisterTime.appId12 = accountItem.appId12RegisterTime;

        appIdRegisterTime.appId13 = accountItem.appId13RegisterTime;

        appIdRegisterTime.appId14 = accountItem.appId14RegisterTime;

        appIdRegisterTime.appId15 = accountItem.appId15RegisterTime;

        appIdRegisterTime.appId16 = accountItem.appId16RegisterTime;

        appIdRegisterTime.appId17 = accountItem.appId17RegisterTime;

        appIdRegisterTime.appId18 = accountItem.appId18RegisterTime;

        appIdRegisterTime.appId19 = accountItem.appId19RegisterTime;

        appIdRegisterTime.appId20 = accountItem.appId20RegisterTime;

        appIdRegisterTime.appId21 = accountItem.appId21RegisterTime;

        appIdRegisterTime.appId22 = accountItem.appId22RegisterTime;

        appIdRegisterTime.appId23 = accountItem.appId23RegisterTime;

        appIdRegisterTime.appId24 = accountItem.appId24RegisterTime;

        appIdRegisterTime.appId25 = accountItem.appId25RegisterTime;

        appIdRegisterTime.appId26 = accountItem.appId26RegisterTime;

        appIdRegisterTime.appId27 = accountItem.appId27RegisterTime;

        appIdRegisterTime.appId28 = accountItem.appId28RegisterTime;

        appIdRegisterTime.appId29 = accountItem.appId29RegisterTime;

        appIdRegisterTime.appId30 = accountItem.appId30RegisterTime;

        appIdRegisterTime.appId31 = accountItem.appId31RegisterTime;

        appIdRegisterTime.appId32 = accountItem.appId32RegisterTime;

        appIdRegisterTime.appId33 = accountItem.appId33RegisterTime;

        appIdRegisterTime.appId34 = accountItem.appId34RegisterTime;

        appIdRegisterTime.appId35 = accountItem.appId35RegisterTime;

        appIdRegisterTime.appId36 = accountItem.appId36RegisterTime;

        appIdRegisterTime.appId37 = accountItem.appId37RegisterTime;

        appIdRegisterTime.appId38 = accountItem.appId38RegisterTime;

        appIdRegisterTime.appId39 = accountItem.appId39RegisterTime;

        appIdRegisterTime.appId40 = accountItem.appId40RegisterTime;

        appIdRegisterTime.appId41 = accountItem.appId41RegisterTime;

        appIdRegisterTime.appId42 = accountItem.appId42RegisterTime;

        appIdRegisterTime.appId43 = accountItem.appId43RegisterTime;

        appIdRegisterTime.appId44 = accountItem.appId44RegisterTime;

        appIdRegisterTime.appId45 = accountItem.appId45RegisterTime;

        appIdRegisterTime.appId46 = accountItem.appId46RegisterTime;

        return JSON.toJSONString(appIdRegisterTime);
    }

}
