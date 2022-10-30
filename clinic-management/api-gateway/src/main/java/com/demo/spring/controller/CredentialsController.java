package com.demo.spring.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.ServerConfiguration;
import com.demo.spring.dto.CredentialsDTO;
import com.demo.spring.entity.Credentials;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.service.CredentialsService;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@OpenAPI30
@RequestMapping("/login")
@EnableConfigurationProperties(ServerConfiguration.class)
public class CredentialsController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
 
	@Autowired
	CredentialsService credentialsService;
/**
 * this method will verify user credentials
 * @param credentialsDTO
 * @return
 * @throws UserNotFoundException
 */
	@PostMapping(path = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> findUserName(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		logger.info("this method had a call to verify credential service");
		return ResponseEntity.ok(credentialsService.findUserNameService(credential));
   
	}
/**
 * this method will update the user password
 * @param credentialsDTO
 * @return
 * @throws UserNotFoundException
 */
	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePassword(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		logger.info("this method had a call to update user service");
		return ResponseEntity.ok(credentialsService.updateUser(credential));
	}

}
