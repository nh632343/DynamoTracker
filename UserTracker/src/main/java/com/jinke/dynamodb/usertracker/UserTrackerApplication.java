package com.jinke.dynamodb.usertracker;

import com.jinke.dynamodb.usertracker.worker.AccountWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.usertracker"})
@EnableScheduling
public class UserTrackerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UserTrackerApplication.class, args);
		AccountWorker accountWorker = context.getBean(AccountWorker.class);
		System.out.println(accountWorker.accountDynamoDao.toString());
        //accountWorker.run();
	}
}
