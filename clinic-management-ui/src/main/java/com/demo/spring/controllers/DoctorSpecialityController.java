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

import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.DoctorSpecialityDTO;
@Controller
public class DoctorSpecialityController {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@GetMapping(path = "/doctorSpeciality/listdoctorinspeciality")
	public ModelAndView listDoctorInSpeciality(@RequestParam(name = "specialityID", required = true) String specialityID) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		ResponseEntity<List<DoctorDTO>> response = restTemplate.exchange(
				"http://localhost:8194/clinic/speciality/list/" + specialityID, HttpMethod.GET, request,
				new ParameterizedTypeReference<List<DoctorDTO>>() {
				});

		if (response.getBody() != null) {
			mv.addObject("doctorList", response.getBody());
			mv.setViewName("listDoctorSpeciality");
		} else {
			ResponseEntity<String> response3 = restTemplate.exchange("http://localhost:8194/clinic/speciality/list/" + specialityID,
					HttpMethod.GET, request, String.class);
			mv.addObject("msg", response3.getBody());
			mv.setViewName("findpatientfailure");
		}
		return mv;
	}
	
	@PostMapping(path = "/doctorSpeciality/addDoctorToSpeciality")
	public ModelAndView addDoctorSpeciality(DoctorSpecialityDTO doctorSpecialityDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<DoctorSpecialityDTO> req = new HttpEntity<>(doctorSpecialityDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/speciality/addDoctor", HttpMethod.POST,
				req, String.class);
		mv.setViewName("savepatientsuccess");
		mv.addObject("response", response.getBody());
		return mv;
	}
	
	@PostMapping(path = "/removedoctor")
	public ModelAndView removedoctorfromspeciality(@RequestParam(name = "doctorID", required = true) int doctorID,@RequestParam(name = "specialityID", required = true) int specialityID) {
		System.out.println("iiiiiiiiiiiiiiiisdcnj");
		
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
			ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/speciality/removeDoctor/"+doctorID+"/"+specialityID,
					HttpMethod.DELETE, req, String.class);
			mv.addObject("response", response.getBody());
			mv.setViewName("deleteDoctorSpeciality");
		return mv;

}
}
