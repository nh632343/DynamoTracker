package com.jinke.basecommon.worker;

import com.alibaba.fastjson.JSON;
import com.jinke.basecommon.deliver.WorkerDeliver;
import com.jinke.basecommon.entity.*;
import com.jinke.basecommon.entity.json.PlatformInfo;
import com.jinke.basecommon.utils.AccountUtils;
import com.jinke.basecommon.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountItemResultWorker implements Runnable {

    List<AccountItem> mScanResultPage;

    public AccountItemResultWorker(List<AccountItem> scanResultPage) {
        mScanResultPage = scanResultPage;
    }

    @Override
    public void run() {
//        if (ArrayUtils.isEmpty(mScanResultPage)) return;
//        List<ThirdUserItem> thirdUserItemList = new ArrayList<>();
//        List<UserItem> userItemList = new ArrayList<>();
//        List<UserDetailItem> userDetailItemList = new ArrayList<>();
//
//        List<AccountItem> accountItems = mScanResultPage;
//        for (AccountItem accountItem : accountItems) {
//            Integer accountType = accountItem.getAccountType();
//            //if (accountType == 1) continue;
//            if (accountType == null || accountType > 4) {
//                ThirdUserItem thirdUserItem = new ThirdUserItem();
//                thirdUserItem.setAccountTypeOpenId(accountItem.getPlamformId() + "#" + accountItem.getOpenId());
//                thirdUserItem.setOpenId(accountItem.getOpenId());
//                thirdUserItem.setRegisterTime(accountItem.getRegisterTime());
//
//                PlatformInfo platformInfo = new PlatformInfo();
//                platformInfo.setChannelId(accountItem.getChannelId());
//                platformInfo.setPlatformId(accountItem.getPlamformId());
//                thirdUserItem.setPlatformInfo(JSON.toJSONString(platformInfo));
//
//                thirdUserItem.setAppIdRegisterTime(AccountUtils.getAppIdRegisterTime(accountItem));
//
//                thirdUserItemList.add(thirdUserItem);
//
//            } else if (accountType == 1) {
//                continue;
//            } else {  // accountType is 2 or 3 or 4
//                UserItem userItem = new UserItem();
//                userItem.setAccountName(AccountUtils.getAccountTypeString(accountType) + "#" + accountItem.getAccountName());
//                userItem.setPwd(accountItem.getPwd());
//                userItem.setOpenId(accountItem.getOpenId());
//                userItem.setRegisterTime(accountItem.getRegisterTime());
//                userItem.setAppIdRegisterTime(AccountUtils.getAppIdRegisterTime(accountItem));
//                userItemList.add(userItem);
//
//                UserDetailItem userDetailItem = new UserDetailItem();
//                userDetailItem.setOpenId(accountItem.getOpenId());
//                userDetailItem.setAccountType(accountType);
//                userDetailItem.setRegisterTime(accountItem.getRegisterTime());
//                userDetailItemList.add(userDetailItem);
//
//                //如果是账号绑有手机，再增加一条记录
//                if (accountType == 4 && accountItem.getMobile() != null && !accountItem.getMobile().isEmpty()) {
//                    UserItem mobileUserItem = new UserItem();
//                    mobileUserItem.setAccountName(AccountUtils.getAccountTypeString(2) + "#" + accountItem.getMobile());
//                    mobileUserItem.setPwd(userItem.getPwd());
//                    mobileUserItem.setOpenId(userItem.getOpenId());
//                    mobileUserItem.setRegisterTime(userItem.getRegisterTime());
//                    mobileUserItem.setAppIdRegisterTime(userItem.getAppIdRegisterTime());
//                    userItemList.add(mobileUserItem);
//                }
//            }
//        }
//
//        if (!ArrayUtils.isEmpty(thirdUserItemList)) {
//            WorkerDeliver.deliverWrite(thirdUserItemList);
//        }
//
//        if (!ArrayUtils.isEmpty(userItemList)) {
//            WorkerDeliver.deliverWrite(userItemList);
//        }
//
//        if (!ArrayUtils.isEmpty(userDetailItemList)) {
//            WorkerDeliver.deliverWrite(userDetailItemList);
//        }
    }

    public static void handleAccountItems(List<AccountItem> accountItems) {
        if (ArrayUtils.isEmpty(accountItems)) return;
        List<ThirdUserItem> thirdUserItemList = new ArrayList<ThirdUserItem>();
        List<UserItem> userItemList = new ArrayList<UserItem>();
        List<UserDetailItem> userDetailItemList = new ArrayList<UserDetailItem>();

        for (AccountItem accountItem : accountItems) {
            Integer accountType = accountItem.getAccountType();
            //if (accountType == 1) continue;
            if (accountType == null || accountType > 4) {
                ThirdUserItem thirdUserItem = new ThirdUserItem();
                thirdUserItem.setAccountTypeOpenId(accountItem.getPlamformId() + "#" + accountItem.getOpenId());
                thirdUserItem.setOpenId(accountItem.getOpenId());
                thirdUserItem.setRegisterTime(accountItem.getRegisterTime());

                PlatformInfo platformInfo = new PlatformInfo();
                platformInfo.setChannelId(accountItem.getChannelId());
                platformInfo.setPlatformId(accountItem.getPlamformId());
                thirdUserItem.setPlatformInfo(JSON.toJSONString(platformInfo));

                thirdUserItem.setAppIdRegisterTime(AccountUtils.getAppIdRegisterTime(accountItem));

                thirdUserItemList.add(thirdUserItem);

            } else if (accountType == 1) {
                continue;
            } else {  // accountType is 2 or 3 or 4
                UserItem userItem = new UserItem();
                userItem.setAccountName(AccountUtils.getAccountTypeString(accountType) + "#" + accountItem.getAccountName());
                userItem.setPwd(accountItem.getPwd());
                userItem.setOpenId(accountItem.getOpenId());
                userItem.setRegisterTime(accountItem.getRegisterTime());
                userItem.setAppIdRegisterTime(AccountUtils.getAppIdRegisterTime(accountItem));
                userItemList.add(userItem);

                UserDetailItem userDetailItem = new UserDetailItem();
                userDetailItem.setOpenId(accountItem.getOpenId());
                userDetailItem.setAccountType(accountType);
                userDetailItem.setRegisterTime(accountItem.getRegisterTime());
                userDetailItemList.add(userDetailItem);

                //如果是账号绑有手机，再增加一条记录
                if (accountType == 4 && accountItem.getMobile() != null && !accountItem.getMobile().isEmpty()) {
                    UserItem mobileUserItem = new UserItem();
                    mobileUserItem.setAccountName(AccountUtils.getAccountTypeString(2) + "#" + accountItem.getMobile());
                    mobileUserItem.setPwd(userItem.getPwd());
                    mobileUserItem.setOpenId(userItem.getOpenId());
                    mobileUserItem.setRegisterTime(userItem.getRegisterTime());
                    mobileUserItem.setAppIdRegisterTime(userItem.getAppIdRegisterTime());
                    userItemList.add(mobileUserItem);
                }
            }
        }

        if (!ArrayUtils.isEmpty(thirdUserItemList)) {
            WorkerDeliver.deliverWrite(thirdUserItemList);
        }

        if (!ArrayUtils.isEmpty(userItemList)) {
            WorkerDeliver.deliverWrite(userItemList);
        }

        if (!ArrayUtils.isEmpty(userDetailItemList)) {
            WorkerDeliver.deliverWrite(userDetailItemList);
        }
    }

}
