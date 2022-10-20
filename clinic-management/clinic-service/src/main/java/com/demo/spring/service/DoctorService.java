package com.demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Doctor;
import com.demo.spring.repositories.DoctorRepository;

@Service
public class DoctorService {

	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> listAllDoctorService(){
		return doctorRepository.findAll();
	}
}
