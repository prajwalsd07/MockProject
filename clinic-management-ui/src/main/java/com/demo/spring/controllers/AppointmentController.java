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

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.PatientDTO;

@Controller
public class AppointmentController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/appointment/listappointment")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<List<AppointmentDTO>> response = restTemplate.exchange("http://localhost:8194/appointment/list",
				HttpMethod.GET, req, new ParameterizedTypeReference<List<AppointmentDTO>>() {
				});
		mv.addObject("appointmentlist", response.getBody());
		mv.setViewName("listAppointment");
		return mv;
	}

	@PostMapping(path = "/appointment/save")
	public ModelAndView saveAppointment(AppointmentDTO appointmentDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<AppointmentDTO> req = new HttpEntity<>(appointmentDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/appointment/add",
				HttpMethod.POST, req, String.class);

		mv.addObject("response", response.getBody());
		mv.setViewName("saveAppointment");
		return mv;
	}

	@GetMapping(path = "/appointment/listByDate")
	public ModelAndView listAppointmentByDate(@RequestParam(name = "doctorID", required = true) int doctorID,
			@RequestParam(name = "date", required = true) String date) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		String str = restTemplate.exchange("http://localhost:8194/appointment/listByDate/"+doctorID +"/"+ date,
				HttpMethod.GET, request, String.class).getBody();
		if (str != null && str.equals("{\"status\":\"There are no Appointments\"}")) {
			mv.addObject("msg", "There are no Appointments");
			mv.setViewName("findpatientfailure");
			return mv;
		} else {
			ResponseEntity<List<AppointmentDTO>> response = restTemplate.exchange(
					"http://localhost:8194/appointment/listByDate/" + doctorID + "/" + date, HttpMethod.GET, request,
					new ParameterizedTypeReference<List<AppointmentDTO>>() {
					});
			
			System.out.println(response.getBody());
			mv.addObject("appointmentList", response.getBody());
			mv.setViewName("listAppointmentDate");
			return mv;
		}
	}
}