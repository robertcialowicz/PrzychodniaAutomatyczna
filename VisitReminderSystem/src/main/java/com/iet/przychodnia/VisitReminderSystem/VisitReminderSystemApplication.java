package com.iet.przychodnia.VisitReminderSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VisitReminderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitReminderSystemApplication.class, args);
	}

}
