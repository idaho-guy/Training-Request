package com.teamproject.trainingrequest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableFeignClients
@Sql({"/test-schema.sql", "/test-data.sql"})
public class TrainingRequestControllerIntegrationTests {

	@Test
	public void getOpenTrainingRequests() {
	}



}
