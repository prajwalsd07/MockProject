package com.demo.spring;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = {
		"patientServer=http://localhost:${wiremock.server.port}",
		"clinicServer=http://localhost:${wiremock.server.port}" })

@AutoConfigureWireMock(port = 0)
class ApplicationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testAddAppointmentPatientFailure() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		stubFor(get(urlEqualTo("/clinic/doctor/1")).willReturn(
				aResponse().withHeader("Content-Type", "application/json").withBody("{\"name\":\"Prajwal\"}")));
		webClient.get().uri("/clinic/doctor/1").exchange().expectStatus().isOk().expectBody().jsonPath("$.name")
				.isEqualTo("Prajwal");

	}

}
