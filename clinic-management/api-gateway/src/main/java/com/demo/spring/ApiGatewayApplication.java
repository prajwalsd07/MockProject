package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableConfigurationProperties(ServerConfiguration.class)
public class ApiGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, ServerConfiguration uriConfiguration) {
		String patientServer = uriConfiguration.getPatientServer();
		String appointmentServer = uriConfiguration.getAppointmentServer();
		String clinicServer = uriConfiguration.getClinicServer();
		return builder.routes().route(p -> p.path("/patient/**").uri(patientServer))
				.route(p -> p.path("/appointment/**").uri(appointmentServer))
				.route(p -> p.path("/clinic/**").uri(clinicServer)).build();
	}
	
	@Bean
	public OpenAPI openApiDoc() {
		return new OpenAPI().info(new Info().description("Mock prject on Clinic Management")
		       .license(new License().name("Free for All")).title("Mock Project Document").version("1.0.0-BETA"));
	}
	
}
