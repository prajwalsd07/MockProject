package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Credentials;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.repository.CredentialsRepository;
import com.demo.spring.util.Message;

@Service
public class CredentialsService {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	@Autowired
	CredentialsRepository credentialRepository;

	public Message findUserNameService(Credentials credentials) throws UserNotFoundException {
		List<Credentials> listCred = credentialRepository.findUser(credentials.getUserName(),
				credentials.getPassword());
		if (listCred.isEmpty()) {
			logger.error("Exception : User Not Found Exception thrown");
			throw new UserNotFoundException();
		} else {
			logger.info("user credentials successfully verified");
			return new Message("User Found");
		}
	} 
 
	public Message updateUser(Credentials credentials) throws UserNotFoundException {
		Optional<Credentials> credOp = credentialRepository.findById(credentials.getUserName());
		if (credOp.isEmpty()) {
			logger.error("Exception : User Not Found Exception thrown");
			throw new UserNotFoundException();
		} else {
			credentialRepository.updateUser(credentials.getUserName(), credentials.getPassword());
			logger.info("user credentials successfully updated");
			return new Message("User Updated");
		}
	}
}