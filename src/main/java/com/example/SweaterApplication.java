package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.example.repos")
@SpringBootApplication
public class SweaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweaterApplication.class, args);
	}

}
