package com.demo.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.DoctorSpecialityDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.DoctorSpecialityNotFoundException;
import com.demo.spring.exceptions.SpecialityNotFoundException;
import com.demo.spring.service.DoctorSpecialityService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DoctorSpecialityRestController {

	@Autowired
	DoctorSpecialityService doctorSpecialityService;

	/*
	this will list all the doctors in a particular speciality
	*/
	@Timed(value = "requests.list.doctor.speciality")
	@GetMapping(path = "/speciality/list/{specialityID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listDoctorinSpeciality(@PathVariable("specialityID") Integer specialityID) throws SpecialityNotFoundException, DoctorNotFoundException {
		return ResponseEntity.ok(doctorSpecialityService.listDoctorInSpeciality(specialityID));
	}
	
	/*
	this will add new doctor to a speciality
	*/
	@Timed(value = "requests.add.doctor.speciality")
	@PostMapping(path="/speciality/addDoctor" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> addDoctorToSpeciality(@RequestBody DoctorSpecialityDTO doctorSpecialityDTO) throws DoctorNotFoundException{
		return ResponseEntity.ok(doctorSpecialityService.addDoctorService(doctorSpecialityDTO));
    }
	
	/*
	this will remove doctor from speciality
	*/
	@Timed(value = "requests.remove.doctor.speciality")
	@DeleteMapping(path = "/peciality/removeDoctor/{doctorID}/{specialityID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeDoctorFromSpeciality(@PathVariable("doctorID") Integer doctorId,@PathVariable("specialityID") Integer specialityId) throws DoctorSpecialityNotFoundException {
		return ResponseEntity.ok(doctorSpecialityService.removeDoctorFromSpecialityService(doctorId,specialityId));
	}

}
