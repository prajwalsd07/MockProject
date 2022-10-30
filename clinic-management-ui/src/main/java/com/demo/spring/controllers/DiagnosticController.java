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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.DiagnosticDTO;
import com.demo.spring.dto.PatientDTO;

@Controller
public class DiagnosticController {

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(path = "/diagnostic/listDiagnostic")
	public ModelAndView listAllDiagnostics() {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<List<DiagnosticDTO>> response = restTemplate.exchange("http://localhost:8194/clinic/diagnostic/list", HttpMethod.GET, req, new ParameterizedTypeReference<List<DiagnosticDTO>>(){});
		mv.addObject("diagnosticlist",response.getBody());
		mv.setViewName("listDiagnostic");
		return mv;
	}
	
	
	@PostMapping(path = "/diagnostic/delete2")
	public ModelAndView findById(@RequestParam(name = "diagnosticID", required = true) int diagnosticID) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/diagnostic/deleteTest/" +diagnosticID,
					HttpMethod.DELETE, req, String.class);
			mv.addObject("response", response.getBody());
			mv.setViewName("deleteDiagnostic");
		return mv;

	}
	@PostMapping(path = "/diagnostic/saveDiagnostic")
	public ModelAndView savePatient(DiagnosticDTO diagnosticDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<DiagnosticDTO> req = new HttpEntity<>(diagnosticDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/diagnostic/addTest", HttpMethod.POST,
				req, String.class);
		
		mv.addObject("response", response.getBody());
		mv.setViewName("saveDiagnostic");
		return mv;
	}
}
