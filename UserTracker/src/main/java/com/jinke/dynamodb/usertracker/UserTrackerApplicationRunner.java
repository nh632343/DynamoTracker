package com.jinke.dynamodb.usertracker;




import com.jinke.dynamodb.usertracker.worker.AccountWorker;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;


import org.springframework.stereotype.Component;



@Component
public class UserTrackerApplicationRunner implements ApplicationRunner {

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