package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;
@ControllerAdvice
public class AppointmentNotFoundException extends Exception{
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<Message> noAppointment(AppointmentNotFoundException ex){
		return ResponseEntity.ok(new Message("There are no Appointments"));
}
}
