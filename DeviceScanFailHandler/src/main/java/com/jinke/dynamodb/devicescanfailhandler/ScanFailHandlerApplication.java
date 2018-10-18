package com.jinke.dynamodb.devicescanfailhandler;

import com.jinke.dynamodb.devicescanfailhandler.worker.ReScanWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.devicescanfailhandler"})
@EnableScheduling
public class ScanFailHandlerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ScanFailHandlerApplication.class, args);
		ReScanWorker reScanWorker = context.getBean(ReScanWorker.class);
		reScanWorker.run();
	}
}
