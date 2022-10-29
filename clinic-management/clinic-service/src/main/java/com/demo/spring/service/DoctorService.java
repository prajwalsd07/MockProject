package com.demo.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.repositories.DoctorRepository;
import com.demo.spring.util.Message;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	public List<Doctor> listAllDoctorService() {
		return doctorRepository.findAll();
	}

	public ResponseEntity<Doctor> findDoctorService(int doctorId) throws DoctorNotFoundException {
		Optional<Doctor> doctorOp = doctorRepository.findById(doctorId);
		if (doctorOp.isPresent()) {
			return ResponseEntity.ok(doctorOp.get());
		} else {
			throw new DoctorNotFoundException();
		}

	}
	

}
