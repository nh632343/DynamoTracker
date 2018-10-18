package com.jinke.stream.utils;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.jinke.basecommon.entity.AccountItem;

import java.util.Map;

public class ConvertUtils {

    public static AccountItem toAccountItem(Map<String, AttributeValue> map) {
        AccountItem accountItem = new AccountItem();
        AttributeValue attributeValue = null;

        attributeValue = map.get("account_name");
        if (attributeValue != null) {
            accountItem.setAccountName(attributeValue.getS());
        }

        attributeValue = map.get("open_id");
        if (attributeValue != null) {
            accountItem.setOpenId(attributeValue.getS());
        }

        attributeValue = map.get("mobile");
        if (attributeValue != null) {
            accountItem.setMobile(attributeValue.getS());
        }

        attributeValue = map.get("password");
        if (attributeValue != null) {
            accountItem.setPwd(attributeValue.getS());
        }

        attributeValue = map.get("account_type");
        if (attributeValue != null) {
            try {
                accountItem.setAccountType(Integer.parseInt(attributeValue.getN()));
            } catch (Exception e) {e.printStackTrace();}
        }

        attributeValue = map.get("platform_id");
        if (attributeValue != null) {
            try {
                accountItem.setPlamformId(Integer.parseInt(attributeValue.getN()));
            } catch (Exception e) {e.printStackTrace();}
        }

        attributeValue = map.get("channel_id");
        if (attributeValue != null) {
            try {
                accountItem.setChannelId(Integer.parseInt(attributeValue.getN()));
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("register_time");
        if (attributeValue != null) {
            try {
                accountItem.setRegisterTime(Long.parseLong(attributeValue.getN()));
            } catch (Exception e) {e.printStackTrace();}
        }

        attributeValue = map.get("app_id_1_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId1RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }

        attributeValue = map.get("app_id_1_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId1RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_2_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId2RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_3_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId3RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_4_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId4RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_5_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId5RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_6_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId6RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_7_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId7RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_8_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId8RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_9_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId9RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_10_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId10RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_11_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId11RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_12_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId12RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_13_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId13RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_14_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId14RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_15_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId15RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_16_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId16RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_17_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId17RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_18_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId18RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_19_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId19RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_20_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId20RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_21_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId21RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_22_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId22RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_23_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId23RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_24_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId24RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_25_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId25RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_26_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId26RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_27_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId27RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_28_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId28RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_29_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId29RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_30_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId30RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_31_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId31RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_32_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId32RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_33_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId33RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_34_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId34RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_35_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId35RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_36_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId36RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_37_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId37RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_38_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId38RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_39_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId39RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_40_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId40RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_41_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId41RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_42_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId42RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_43_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId43RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_44_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId44RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_45_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId45RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }


        attributeValue = map.get("app_id_46_register_time");
        if (attributeValue != null) {
            try {
                accountItem.appId46RegisterTime = Integer.parseInt(attributeValue.getN());
            } catch (Exception e) {e.printStackTrace();}
        }

        return accountItem;
    }
}
