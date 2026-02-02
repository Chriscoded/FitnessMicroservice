package com.fitness.userService;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@PostConstruct
	public void logEurekaInstanceInfo() {
		System.out.println("HOSTNAME = " + System.getProperty("eureka.instance.hostname"));
		System.out.println("IP = " + System.getProperty("eureka.instance.ip-address"));
	}

}


