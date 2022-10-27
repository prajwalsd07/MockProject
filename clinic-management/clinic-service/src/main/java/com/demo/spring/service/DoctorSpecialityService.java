package com.demo.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.DoctorSpecialityDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.entity.DoctorSpeciality;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.DoctorSpecialityNotFoundException;
import com.demo.spring.exceptions.SpecialityNotFoundException;
import com.demo.spring.repositories.DoctorRepository;
import com.demo.spring.repositories.DoctorSpecialityRepository;
import com.demo.spring.repositories.SpecialityRepository;
import com.demo.spring.util.Message;

@Service
public class DoctorSpecialityService {

	@Autowired
	DoctorSpecialityRepository doctorSpecialityRepository;

	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	SpecialityRepository specialityRepository;

	public Message addDoctorService(DoctorSpecialityDTO doctorSpecialityDTO) throws DoctorNotFoundException {
		if (doctorRepository.existsById(doctorSpecialityDTO.getDoctorID())) {
			DoctorSpeciality doctorSpeciality = new DoctorSpeciality(doctorSpecialityDTO.getId(),doctorSpecialityDTO.getDoctorID(),
					doctorSpecialityDTO.getSpecialityID());
			doctorSpecialityRepository.save(doctorSpeciality);
			return new Message("Doctor added to speciality");
		} else {
			throw new DoctorNotFoundException();
		}

	}

	public Message removeDoctorFromSpecialityService(Integer doctorID) throws DoctorSpecialityNotFoundException {
		List<DoctorSpeciality> doctorList = doctorSpecialityRepository.findByDoctorID(doctorID);
		if (doctorList.isEmpty()) {
			throw new DoctorSpecialityNotFoundException();
		} else {
			for(DoctorSpeciality doctorSpeciality : doctorList)
			{
			doctorSpecialityRepository.delete(doctorSpeciality);
			}
			return new Message("Doctor Removed from Speciality");
		}

	}

	public List<Doctor> listDoctorInSpeciality(Integer specialityID) throws SpecialityNotFoundException, DoctorNotFoundException   {
			List<DoctorSpeciality> doctorIdList = doctorSpecialityRepository.listDoctorInSpeciality(specialityID);
			Integer i = 0;
			List<Doctor> doctorList = new ArrayList<>();
			if(doctorIdList.isEmpty())
			{
				throw new DoctorNotFoundException();
			}else
			{
				for (doctorRepository.existsById(doctorIdList.get(i).getDoctorID()); i < doctorIdList.size(); i++) {

					Optional<Doctor> doctorOps = doctorRepository.findById(doctorIdList.get(i).getDoctorID());
					if (doctorOps.isPresent()) {
						doctorList.add(doctorOps.get());
					}
				}
				if (doctorList.isEmpty()) {
					throw new SpecialityNotFoundException();
				} else {
					return doctorList;
				}
			}
			}
			
	}
