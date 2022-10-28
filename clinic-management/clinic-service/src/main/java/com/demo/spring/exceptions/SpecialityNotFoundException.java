package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;

@ControllerAdvice
public class SpecialityNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler
	public ResponseEntity<Message> SpecialityNotFound(SpecialityNotFoundException ex){
		return ResponseEntity.ok(new Message("Speciality Not Found"));
	}

}