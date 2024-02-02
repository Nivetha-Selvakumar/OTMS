package com.onlinetaskmanagementsystem.otms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)

public class OtmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(OtmsApplication.class, args);
	}

}
