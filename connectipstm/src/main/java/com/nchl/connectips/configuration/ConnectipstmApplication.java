package com.nchl.connectips.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nchl.connectips")
public class ConnectipstmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectipstmApplication.class, args);
	}
}
