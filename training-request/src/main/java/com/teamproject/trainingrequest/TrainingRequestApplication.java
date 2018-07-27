package com.teamproject.trainingrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TrainingRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingRequestApplication.class, args);
	}
}
