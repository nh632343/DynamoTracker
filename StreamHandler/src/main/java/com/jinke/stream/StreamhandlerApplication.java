package com.jinke.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.jinke.basecommon", "com.jinke.stream"})
public class StreamhandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamhandlerApplication.class, args);
	}
}
