package com.demo.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.PatientDTO;

@Controller
public class PatientController {
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/patient/findPatient")
	public ModelAndView findById(@RequestParam(name = "id", required = true) int id) {
		ModelAndView mv = new ModelAndView();
		String message = "Patient Not Found";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<PatientDTO> response = restTemplate.exchange("http://localhost:8194/patient/" + id,
				HttpMethod.GET, req, PatientDTO.class);
		if (response.getBody().getFirstName() != null) {
			mv.addObject("patient", response.getBody());
			mv.setViewName("findPatient");
		} else {
			ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8194/patient/" + id,
					HttpMethod.GET, req, String.class);
			mv.addObject("msg", message);
			mv.setViewName("findpatientfailure");
		}
		 
		return mv;

	}

	@GetMapping(path = "/patient/listp")
	public ModelAndView listpatient(@RequestParam(name = "firstName", required = true) String firstName) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
				"http://localhost:8194/patient/list/" + firstName, HttpMethod.GET, request,
				new ParameterizedTypeReference<List<PatientDTO>>() {
				});

		if (response.getBody() != null) {
			mv.addObject("patientList", response.getBody());
			mv.setViewName("listPatient");
		} else {
			System.out.println("nononononoonoooooooooooooooooooooooooooo");
			ResponseEntity<String> response3 = restTemplate.exchange("http://localhost:8194/patient/list/" + firstName,
					HttpMethod.GET, request, String.class);
			mv.addObject("msg", response3.getBody());
			mv.setViewName("findpatientfailure");
		}
		return mv;
	}

	@PostMapping(path = "/patient/savenewPatient")
	public ModelAndView savePatient(PatientDTO patientDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PatientDTO> req = new HttpEntity<>(patientDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/patient/save", HttpMethod.POST,
				req, String.class);
		mv.setViewName("savepatientsuccess");
		mv.addObject("response", response.getBody());
		return mv;
	}

	@PostMapping(path = "/patient/updateoldPatient")
	public ModelAndView updatePatient(PatientDTO patientDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PatientDTO> req = new HttpEntity<>(patientDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/patient/update",
				HttpMethod.PATCH, req, String.class);
		mv.setViewName("updatepatientsuccess");
		mv.addObject("response", response.getBody());
		return mv;
	}

}
