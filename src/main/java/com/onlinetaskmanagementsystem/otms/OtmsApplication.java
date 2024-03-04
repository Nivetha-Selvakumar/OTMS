package com.onlinetaskmanagementsystem.otms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan("com.onlinetaskmanagementsystem.otms")
@EnableScheduling
public class OtmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(OtmsApplication.class, args);
	}

}
