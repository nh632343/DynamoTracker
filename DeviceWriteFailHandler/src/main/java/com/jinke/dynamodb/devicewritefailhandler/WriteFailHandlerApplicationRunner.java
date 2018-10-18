package com.jinke.dynamodb.devicewritefailhandler;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


import org.springframework.stereotype.Component;



@Component
public class WriteFailHandlerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) {
           System.out.println("run application  -----------------------");
           startWork();
    }

    private void startWork() {
        //new Thread(new AccountWorker()).start();
        /*AccountWorker accountWorker = UserTrackerApplication.getContext().getBean(AccountWorker.class);
        System.out.println(accountWorker.accountDynamoDao);*/
    }

}