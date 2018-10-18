package com.jinke.dynamodb.devicewritefailhandler;

import com.jinke.dynamodb.devicewritefailhandler.worker.ReQueryWorker;
import com.jinke.dynamodb.devicewritefailhandler.worker.ReWriteWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.devicewritefailhandler"})
@EnableScheduling
public class WriteFailHandlerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WriteFailHandlerApplication.class, args);
		ReQueryWorker reQueryWorker = context.getBean(ReQueryWorker.class);
		new Thread(reQueryWorker).start();

		ReWriteWorker reWriteWorker = context.getBean(ReWriteWorker.class);
		reWriteWorker.run();
	}
}
