package com.iet.przychodnia.user_authentication_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableFeignClients
public class UserAuthenticationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationSystemApplication.class, args);
	}

}
