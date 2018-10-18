package com.jinke.dynamodb.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.test"})
public class TestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestApplication.class, args);
        TestWorker testWorker = context.getBean(TestWorker.class);
        TestWorker.singleTon = testWorker;
        testWorker.run();
    }
}
