package com.example.backendgym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.backendgym.repository")
@EntityScan(basePackages = "com.example.backendgym.domain")
@EnableScheduling
@EnableAsync
public class BackendgymApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendgymApplication.class, args);
	}

}
