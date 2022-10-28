package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Credentials;
import com.demo.spring.exception.UserNameExistsException;
import com.demo.spring.exception.UserNotFoundException;
import com.demo.spring.repository.CredentialsRepository;
import com.demo.spring.util.Message;

@Service
public class CredentialsService {

	@Autowired
	CredentialsRepository credentialRepository;

	public Message findUserNameService(Credentials credentials) throws UserNotFoundException {
		List<Credentials> listCred = credentialRepository.findUser(credentials.getUserName(),
				credentials.getPassword());
		if (listCred.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			return new Message("User Found");
		}
	} 
 
	public Message updateUser(Credentials credentials) throws UserNotFoundException {
		Optional<Credentials> credOp = credentialRepository.findById(credentials.getUserName());
		if (credOp.isEmpty()) {
			throw new UserNotFoundException();
		} else {
			credentialRepository.updateUser(credentials.getUserName(), credentials.getPassword());
			return new Message("User Updated");
		}
	}

	public Message addUserService(Credentials credentials) throws UserNameExistsException {
		if (credentialRepository.existsById(credentials.getUserName())) {
			throw new UserNameExistsException();

		} else {
			credentialRepository.save(credentials);
			return new Message("User Added");
		}

	}

	public Message removeUserService(String userName) throws UserNotFoundException {
		Optional<Credentials> credentialList = credentialRepository.findById(userName);
		if (credentialList.isEmpty()) {
			throw new UserNotFoundException();
		} else {

			credentialRepository.deleteById(userName);

			return new Message("User Removed");
		}

	}
}