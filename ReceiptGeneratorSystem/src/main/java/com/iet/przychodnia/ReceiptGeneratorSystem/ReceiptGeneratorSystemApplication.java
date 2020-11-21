package com.iet.przychodnia.ReceiptGeneratorSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ReceiptGeneratorSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptGeneratorSystemApplication.class, args);
	}

}
