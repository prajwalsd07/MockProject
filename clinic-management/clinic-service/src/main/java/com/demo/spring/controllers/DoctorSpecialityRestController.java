package com.demo.spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DoctorSpecialityService doctorSpecialityService;

	/**
	 * this will list all the doctors in a particular speciality
	 * 
	 * @param specialityID
	 * @return
	 * @throws SpecialityNotFoundException
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.list.doctor.speciality")
	@GetMapping(path = "/speciality/list/{specialityID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listDoctorinSpeciality(@PathVariable("specialityID") Integer specialityID)
			throws SpecialityNotFoundException, DoctorNotFoundException {
		logger.info("this method had a call to list doctor in speciality service");
		return ResponseEntity.ok(doctorSpecialityService.listDoctorInSpeciality(specialityID));
	}

	/**
	 * this will add new doctor to a speciality
	 * 
	 * @param doctorSpecialityDTO
	 * @return
	 * @throws DoctorNotFoundException
	 * @throws SpecialityNotFoundException 
	 */
	@Timed(value = "requests.add.doctor.speciality")
	@PostMapping(path = "/speciality/addDoctor", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> addDoctorToSpeciality(@RequestBody DoctorSpecialityDTO doctorSpecialityDTO)
			throws DoctorNotFoundException, SpecialityNotFoundException {
		logger.info("this method had a call to add doctor to speciality service");
		return ResponseEntity.ok(doctorSpecialityService.addDoctorService(doctorSpecialityDTO));
	}

	/**
	 * this will remove doctor from speciality
	 * 
	 * @param doctorId
	 * @param specialityId
	 * @return
	 * @throws DoctorSpecialityNotFoundException
	 */
	@Timed(value = "requests.remove.doctor.speciality")
	@DeleteMapping(path = "/speciality/removeDoctor/{doctorID}/{specialityID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeDoctorFromSpeciality(@PathVariable("doctorID") Integer doctorId,
			@PathVariable("specialityID") Integer specialityId) throws DoctorSpecialityNotFoundException {
		logger.info("this method had a call to remove doctor from speciality service");
		return ResponseEntity.ok(doctorSpecialityService.removeDoctorFromSpecialityService(doctorId, specialityId));
	}

}
