package com.demo.spring;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ClinicManagementUiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ClinicManagementUiApplication.class, args);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/patient").setViewName("homePatient");
		registry.addViewController("/doctor").setViewName("homeDoctor");
		registry.addViewController("/appointment").setViewName("homeAppointment");
		registry.addViewController("/diagnostic").setViewName("homeDiagnostic");
		registry.addViewController("/").setViewName("home");
		
		
		registry.addViewController("/patient-find").setViewName("findPatient");
		registry.addViewController("/patient/list").setViewName("listPatient");
		registry.addViewController("/patient/save").setViewName("savePatient");
		registry.addViewController("/patient-update").setViewName("updatePatient");
		registry.addViewController("/patient").setViewName("homePatient");
		
		
		registry.addViewController("/appointment/add").setViewName("saveAppointment");
		registry.addViewController("/appointment/list").setViewName("listAppointment");
		registry.addViewController("/appointment/listbydate").setViewName("listAppointmentDate");
		registry.addViewController("/appointment").setViewName("homeAppointment");
	
	
		registry.addViewController("/diagnostic/add").setViewName("saveDiagnostic");
		registry.addViewController("/diagnostic/list").setViewName("listDiagnostic");
		registry.addViewController("/diagnostic/delete1").setViewName("deleteDiagnostic");
		registry.addViewController("/diagnostic/addTestPatient").setViewName("saveDiagnosticPatient");
		registry.addViewController("/diagnostic").setViewName("homeDiagnostic");
	
	
		
		registry.addViewController("/doctor-find").setViewName("findDoctor");
		registry.addViewController("/doctor/list").setViewName("listDoctor");
		registry.addViewController("/doctor").setViewName("homeDoctor");
		
		registry.addViewController("/doctorSpeciality/list").setViewName("listDoctorSpeciality");
		registry.addViewController("/doctorSpeciality/add").setViewName("saveDoctorSpeciality");
		registry.addViewController("/doctorSpeciality-remove").setViewName("deleteDoctorSpeciality");
		
		registry.addViewController("/cliniclogin").setViewName("login");
		registry.addViewController("/updatePassword").setViewName("updateLogin");
		
		 registry.addViewController("/error").setViewName("error");
	
	}

	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
       return restTemplate;
    }

}
