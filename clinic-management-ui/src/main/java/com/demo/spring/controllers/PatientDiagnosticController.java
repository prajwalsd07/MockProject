package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.PatientDiagnosticDTO;
@Controller
public class PatientDiagnosticController {
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping(path = "/diagnostic/addDiagnosticPatient")
	public ModelAndView addDiagnosticToPatient(PatientDiagnosticDTO patientDiagnosticDTO) {
		System.out.println("bdffbdfdsgvtebbgsevsdbfgbsrgdrfgbr");
		Integer patientID = patientDiagnosticDTO.getPatientID();
		Integer diagnosticID = patientDiagnosticDTO.getDiagnosticID();
		System.out.println("bdffbdfdsgvtebbgsevsdbfgbsrgdrfgbr");
		System.out.println(" "+patientID+diagnosticID);
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PatientDiagnosticDTO> req = new HttpEntity<>(patientDiagnosticDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/patientDiagnostic/save/"+diagnosticID+"/"+patientID, HttpMethod.POST,
				req, String.class);
		mv.setViewName("savepatientsuccess");
		mv.addObject("response", response.getBody());
		return mv;
	}
}
