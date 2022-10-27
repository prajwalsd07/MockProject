package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.ServerConfiguration;
import com.demo.spring.dto.CredentialsDTO;
import com.demo.spring.entity.Credentials;
import com.demo.spring.exception.UserNameExistsException;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.service.CredentialsService;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@OpenAPI30
@RequestMapping("/login")
@EnableConfigurationProperties(ServerConfiguration.class)
public class CredentialsController {

	@Autowired
	CredentialsService credentialsService;

	@PostMapping(path = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> findUserName(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		return ResponseEntity.ok(credentialsService.findUserNameService(credential));

	}

	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePassword(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		return ResponseEntity.ok(credentialsService.updateUser(credential));
	}

	@PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> addUser(@RequestBody CredentialsDTO credentialsDTO) throws UserNameExistsException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		return ResponseEntity.ok(credentialsService.addUserService(credential));
	}

	@DeleteMapping(path = "/removeUser/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeUser(@PathVariable("userName") String userName)
			throws UserNotFoundException {
		return ResponseEntity.ok(credentialsService.removeUserService(userName));
	}
}
