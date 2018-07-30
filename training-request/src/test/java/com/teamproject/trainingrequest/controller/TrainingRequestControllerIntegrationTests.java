package com.teamproject.trainingrequest.controller;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.teamproject.trainingrequest.model.TrainingRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableFeignClients
@Sql({"/test-schema.sql", "/test-data.sql"})
public class TrainingRequestControllerIntegrationTests {

	static {
		System.setProperty("eureka.client.enabled", "false");
		System.setProperty("spring.cloud.config.failFast", "false");
		System.setProperty("employee.service.url", "http://localhost:8888/");
	}

	@Rule
	public WireMockRule employeeService = new WireMockRule(8888);

	@Autowired
	TestRestTemplate rest;
	private String allOpenRequestsJson;

	@Before
	public void setUp() throws Exception {
		allOpenRequestsJson = getJSON("all-open-requests.json");
		System.out.println(allOpenRequestsJson);
		String allOpenRequests;
	}

	@Test
	public void getOpenTrainingRequests() {
//		ResponseDefinitionBuilder defBuilder = new ResponseDefinitionBuilder();
//		defBuilder.withBody(allOpenRequestsJson);
//		employeeService.stubFor(get(urlEqualTo("/employee-service/employees")).willReturn(defBuilder));




		List<TrainingRequest> requests = rest.getForObject("/trainingrequests", List.class);
		assertEquals(2, requests.size());

	}
	@Test
	public void getOpenTrainingRequestsByCost() {
		List<TrainingRequest> requests = rest.getForObject("/trainingrequests?cost=15", List.class);
		assertEquals(3, requests.size());
		requests = rest.getForObject("/trainingrequests?cost=220", List.class);
		assertEquals(2, requests.size());
		requests = rest.getForObject("/trainingrequests?cost=250", List.class);
		assertEquals(1, requests.size());
	}

	@Test
	public void createNewRequest() throws Exception {

		ResponseDefinitionBuilder defBuilder = new ResponseDefinitionBuilder();
		defBuilder.withBody(getJSON("employee-7.json")).withHeader("Content-Type","application/json");
		employeeService.stubFor(get(urlEqualTo("/employees/7")).willReturn(defBuilder));

		String newRequest = getJSON("request-post.json");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(newRequest, headers);

		ResponseEntity<TrainingRequest> responseEntityResponseEntity =
				rest.postForEntity(new URI("/trainingrequests"), entity, TrainingRequest.class);
		assertEquals(HttpStatus.CREATED,responseEntityResponseEntity.getStatusCode());
		assertEquals("/trainingrequests/1",responseEntityResponseEntity.getHeaders().get("location").get(0));

	}

	private String getJSON(String file) throws Exception {
		Path path = Paths.get(getClass().getResource("/" + file).toURI());
		return Files.lines(path).collect(joining("\n"));
	}




}
