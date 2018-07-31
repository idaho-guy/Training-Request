package com.teamproject.trainingrequest.controller;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.teamproject.trainingrequest.entity.TrainingRequestEntity;
import com.teamproject.trainingrequest.model.TrainingRequest;
import com.teamproject.trainingrequest.repository.TrainingRequestRepository;
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
import static org.junit.Assert.*;

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

	@Autowired
	TrainingRequestRepository trainingRequestRepository;
	private HttpHeaders httpHeaders;

	@Before
	public void setUp() throws Exception {
		allOpenRequestsJson = getJSON("all-open-requests.json");
		System.out.println(allOpenRequestsJson);
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	@Test
	public void getOpenTrainingRequests() {
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
	public void testPostRequest() throws Exception {

		setUpPostExpectations();


		HttpEntity<String> entity = new HttpEntity<String>(getJSON("request-post.json"), httpHeaders);

		ResponseEntity<TrainingRequest> entityForPost =
				rest.postForEntity(new URI("/trainingrequests"), entity, TrainingRequest.class);


		assertEquals(HttpStatus.CREATED, entityForPost.getStatusCode());
		assertEquals("/trainingrequests/1", entityForPost.getHeaders().get("location").get(0));
		TrainingRequestEntity trEntity = trainingRequestRepository.findById(1L).get();
		assertEquals("Chicago", trEntity.getLocation());
		assertEquals("Spring Boot", trEntity.getDescription());
		assertEquals("200.00", trEntity.getCost().toString());
		assertEquals("Vince", trEntity.getRequestedByFirstName());
		assertEquals("Norris", trEntity.getRequestedByLastName());
		assertEquals(new Long(7), trEntity.getEmployeeId());
		assertNull(trEntity.getApprovedDate());
		assertNull(trEntity.getApprovedBy());

	}

	@Test
	public void testPutRequest() throws Exception {
		TrainingRequestEntity trEntity = trainingRequestRepository.findById(10000L).get();
		assertNull(trEntity.getApprovedDate());
		assertNull(trEntity.getApprovedBy());

		HttpEntity entityForPut = new HttpEntity<String>(getJSON("request-put.json"), httpHeaders);

		rest.put(new URI("/trainingrequests/10000"),entityForPut);
		trEntity = trainingRequestRepository.findById(10000L).get();

		assertEquals("cbarbosa", trEntity.getApprovedBy());
		assertNotNull(trEntity.getApprovedDate());

	}

	private void setUpPostExpectations() throws Exception {
		ResponseDefinitionBuilder postBuilder = new ResponseDefinitionBuilder();
		postBuilder.withBody(getJSON("employee-7.json")).withHeader("Content-Type","application/json");
		employeeService.stubFor(get(urlEqualTo("/employees/7")).willReturn(postBuilder));
	}

	private String getJSON(String file) throws Exception {
		Path path = Paths.get(getClass().getResource("/" + file).toURI());
		return Files.lines(path).collect(joining("\n"));
	}




}
