package com.demo.spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;
@ControllerAdvice
public class UserNameExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler
	    public ResponseEntity<Message> userNameExists(UserNameExistsException ex) {
	        return ResponseEntity.ok(new Message("User Name already used"));



	   }
	
}
