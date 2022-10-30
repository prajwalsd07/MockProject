package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.repositories.DoctorRepository;

@Service
public class DoctorService {
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DoctorRepository doctorRepository;

	public List<Doctor> listAllDoctorService() {
		logger.info("all doctor details returned succefully");
		return doctorRepository.findAll();
	}

	public ResponseEntity<Doctor> findDoctorService(int doctorId) throws DoctorNotFoundException {
		Optional<Doctor> doctorOp = doctorRepository.findById(doctorId);
		if (doctorOp.isPresent()) {
			logger.info("doctor details returned succefully for entered Id");
			return ResponseEntity.ok(doctorOp.get());
		} else {
			logger.error("Exception : Doctor Not found Exception thrown");
			throw new DoctorNotFoundException();
		}

	}
	

}
