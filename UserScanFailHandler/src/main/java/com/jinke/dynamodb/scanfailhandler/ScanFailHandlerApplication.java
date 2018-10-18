package com.jinke.dynamodb.scanfailhandler;

import com.jinke.dynamodb.scanfailhandler.worker.ReScanWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.scanfailhandler"})
@EnableScheduling
public class ScanFailHandlerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ScanFailHandlerApplication.class, args);
		ReScanWorker reScanWorker = context.getBean(ReScanWorker.class);
		reScanWorker.run();
	}
}
