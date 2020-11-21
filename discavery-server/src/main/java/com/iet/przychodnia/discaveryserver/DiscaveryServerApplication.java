package com.iet.przychodnia.discaveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscaveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscaveryServerApplication.class, args);
	}

}
