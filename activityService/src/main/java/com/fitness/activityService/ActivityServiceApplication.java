package com.fitness.activityService;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
//@EnableMongoAuditing
public class ActivityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityServiceApplication.class, args);
	}

	@PostConstruct
	public void logEurekaInstanceInfo() {
		System.out.println("HOSTNAME = " + System.getProperty("eureka.instance.hostname"));
		System.out.println("IP = " + System.getProperty("eureka.instance.ip-address"));
	}
}
