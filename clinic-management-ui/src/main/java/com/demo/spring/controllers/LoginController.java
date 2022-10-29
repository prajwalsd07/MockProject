package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.CredentialsDTO;
@Controller
public class LoginController {

	@Autowired
	RestTemplate restTemplate;

	@PostMapping(path = "/verifylogin")
	public ModelAndView validateLogin(CredentialsDTO credentialsDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<CredentialsDTO> req = new HttpEntity<>(credentialsDTO, headers);

		String str = restTemplate.exchange("http://localhost:8194/login/find", HttpMethod.POST, req, String.class)
				.getBody();

		if (str != null && str.equals("{\"status\":\"User Not Found\"}")) {
			mv.addObject("msg", "User Not Found");
			mv.setViewName("findpatientfailure");
			return mv;
		} else {
			mv.setViewName("home");
			return mv;
		}
		}

		
		@PostMapping(path = "/updatelogin")
		public ModelAndView updatePasswordLogin(CredentialsDTO credentialsDTO) {
			ModelAndView mv = new ModelAndView();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<CredentialsDTO> req = new HttpEntity<>(credentialsDTO, headers);

			String str = restTemplate.exchange("http://localhost:8194/login/update", HttpMethod.PATCH, req, String.class)
					.getBody();

			if (str != null && str.equals("{\"status\":\"User Not Found\"}")) {
				mv.addObject("msg", "wrong UserName Entered");
				mv.setViewName("findpatientfailure");
				return mv;
			} else {
				mv.addObject("msg", "User Password Updated");
				mv.setViewName("findpatientfailure");
				return mv;
			}

	}
	
	
}
