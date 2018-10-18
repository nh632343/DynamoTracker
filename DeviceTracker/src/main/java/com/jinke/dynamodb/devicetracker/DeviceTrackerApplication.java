package com.jinke.dynamodb.devicetracker;

import com.jinke.dynamodb.devicetracker.worker.DeviceWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.dynamodb.devicetracker"})
@EnableScheduling
public class DeviceTrackerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DeviceTrackerApplication.class, args);
		DeviceWorker deviceWorker = context.getBean(DeviceWorker.class);
		deviceWorker.run();
	}
}
