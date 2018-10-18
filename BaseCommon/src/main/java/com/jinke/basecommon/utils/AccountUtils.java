package com.jinke.basecommon.utils;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.entity.AccountItem;
import com.jinke.basecommon.entity.AnomymousAccountItem;
import com.jinke.basecommon.entity.json.AppIdRegisterTime;

public class AccountUtils {
    /**
     *
     * @param accountType 只能是2到4
     * @return
     */
    public static String getAccountTypeString(int accountType) {
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
    public static String getAppIdRegisterTime(AccountItem accountItem) {
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

    /**
     *
     * @param accountItem
     * @return
     */
    public static String getAppIdRegisterTime(AnomymousAccountItem accountItem) {
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
