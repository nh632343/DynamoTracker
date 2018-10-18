package com.jinke.dynamodb.writefailhandler;

import com.jinke.dynamodb.writefailhandler.worker.ReWriteWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.writefailhandler"})
@EnableScheduling
public class WriteFailHandlerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WriteFailHandlerApplication.class, args);
		ReWriteWorker reWriteWorker = context.getBean(ReWriteWorker.class);
		reWriteWorker.run();
	}
}
