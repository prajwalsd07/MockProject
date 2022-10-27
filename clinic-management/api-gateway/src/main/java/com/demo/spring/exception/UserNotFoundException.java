package com.demo.spring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;
@ControllerAdvice
public class UserNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler
	    public ResponseEntity<Message> handleUserNotExistsException(UserNotFoundException ex) {
	        return ResponseEntity.ok(new Message("User Not Found"));



	   }
	
}
