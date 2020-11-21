package com.iet.przychodnia.BookVisitSystem;

import com.iet.przychodnia.BookVisitSystem.controller.VisitController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BookVisitSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookVisitSystemApplication.class, args);
	}

}
