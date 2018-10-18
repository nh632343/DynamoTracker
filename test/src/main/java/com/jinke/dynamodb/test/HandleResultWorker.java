package com.jinke.dynamodb.test;

import com.jinke.dynamodb.test.dao.DynamoDao;
import com.jinke.dynamodb.test.entity.AccountItem;
import com.jinke.dynamodb.test.entity.AccountItem2;
import com.jinke.dynamodb.test.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class HandleResultWorker implements Runnable {
    Executor executor;
    List<AccountItem> result;
    long page;
    DynamoDao dynamoDao;

    public HandleResultWorker(Executor executor, List<AccountItem> result, long page, DynamoDao dynamoDao) {
        this.executor = executor;
        this.result = result;
        this.page = page;
        this.dynamoDao = dynamoDao;
    }

    @Override
    public void run() {
        if (ArrayUtils.isEmpty(result)) return;
        List<AccountItem2> toWrite = new ArrayList<>();
        for (AccountItem accountItem : result) {
            AccountItem2 accountItem2 = getAccountItem2(accountItem);
            toWrite.add(accountItem2);
        }

        int totalSize = toWrite.size();
        int curIndex = 0;
        do {
            int last = curIndex + 24;
            if (last > totalSize) last = totalSize;

            executor.execute(new WriteTestWorker(
                    dynamoDao, toWrite.subList(curIndex, last), page)
            );

            curIndex = last;
        } while (curIndex < totalSize);
    }

    private AccountItem2 getAccountItem2(AccountItem accountItem) {
        AccountItem2 accountItem2 = new AccountItem2();
        accountItem2.setAccountName(accountItem.getAccountName());
        accountItem2.setOpenId(accountItem.getOpenId());
        accountItem2.setAccountType(accountItem.getAccountType());
        accountItem2.setMobile(accountItem.getMobile());
        accountItem2.setPwd(accountItem.getPwd());
        accountItem2.setRegisterTime(accountItem.getRegisterTime());
        accountItem2.setPlamformId(accountItem.getPlamformId());
        accountItem2.setChannelId(accountItem.getChannelId());
        accountItem2.appId1RegisterTime = accountItem.appId1RegisterTime;
        accountItem2.appId2RegisterTime = accountItem.appId2RegisterTime;
        accountItem2.appId3RegisterTime = accountItem.appId3RegisterTime;
        accountItem2.appId4RegisterTime = accountItem.appId4RegisterTime;
        accountItem2.appId5RegisterTime = accountItem.appId5RegisterTime;
        accountItem2.appId6RegisterTime = accountItem.appId6RegisterTime;
        accountItem2.appId7RegisterTime = accountItem.appId7RegisterTime;
        accountItem2.appId8RegisterTime = accountItem.appId8RegisterTime;
        accountItem2.appId9RegisterTime = accountItem.appId9RegisterTime;
        accountItem2.appId10RegisterTime = accountItem.appId10RegisterTime;
        accountItem2.appId11RegisterTime = accountItem.appId11RegisterTime;
        accountItem2.appId12RegisterTime = accountItem.appId12RegisterTime;
        accountItem2.appId13RegisterTime = accountItem.appId13RegisterTime;
        accountItem2.appId14RegisterTime = accountItem.appId14RegisterTime;
        accountItem2.appId15RegisterTime = accountItem.appId15RegisterTime;
        accountItem2.appId16RegisterTime = accountItem.appId16RegisterTime;
        accountItem2.appId17RegisterTime = accountItem.appId17RegisterTime;
        accountItem2.appId18RegisterTime = accountItem.appId18RegisterTime;
        accountItem2.appId19RegisterTime = accountItem.appId19RegisterTime;
        accountItem2.appId20RegisterTime = accountItem.appId20RegisterTime;
        accountItem2.appId21RegisterTime = accountItem.appId21RegisterTime;
        accountItem2.appId22RegisterTime = accountItem.appId22RegisterTime;
        accountItem2.appId23RegisterTime = accountItem.appId23RegisterTime;
        accountItem2.appId24RegisterTime = accountItem.appId24RegisterTime;
        accountItem2.appId25RegisterTime = accountItem.appId25RegisterTime;
        accountItem2.appId26RegisterTime = accountItem.appId26RegisterTime;
        accountItem2.appId27RegisterTime = accountItem.appId27RegisterTime;
        accountItem2.appId28RegisterTime = accountItem.appId28RegisterTime;
        accountItem2.appId29RegisterTime = accountItem.appId29RegisterTime;
        accountItem2.appId30RegisterTime = accountItem.appId30RegisterTime;
        accountItem2.appId31RegisterTime = accountItem.appId31RegisterTime;
        accountItem2.appId32RegisterTime = accountItem.appId32RegisterTime;
        accountItem2.appId33RegisterTime = accountItem.appId33RegisterTime;
        accountItem2.appId34RegisterTime = accountItem.appId34RegisterTime;
        accountItem2.appId35RegisterTime = accountItem.appId35RegisterTime;
        accountItem2.appId36RegisterTime = accountItem.appId36RegisterTime;
        accountItem2.appId37RegisterTime = accountItem.appId37RegisterTime;
        accountItem2.appId38RegisterTime = accountItem.appId38RegisterTime;
        accountItem2.appId39RegisterTime = accountItem.appId39RegisterTime;
        accountItem2.appId40RegisterTime = accountItem.appId40RegisterTime;
        accountItem2.appId41RegisterTime = accountItem.appId41RegisterTime;
        accountItem2.appId42RegisterTime = accountItem.appId42RegisterTime;
        accountItem2.appId43RegisterTime = accountItem.appId43RegisterTime;
        accountItem2.appId44RegisterTime = accountItem.appId44RegisterTime;
        accountItem2.appId45RegisterTime = accountItem.appId45RegisterTime;
        accountItem2.appId46RegisterTime = accountItem.appId46RegisterTime;
        return accountItem2;
    }
}
