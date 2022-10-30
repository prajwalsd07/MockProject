package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.DoctorSpecialityDTO;

class DoctorSpecialityTest {
	
	@Test
	void testDoctorSpeciality() {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality();
		doctorSpeciality.setDoctorID(101);
		doctorSpeciality.setSpecialityID(2);
		doctorSpeciality.setId(100);
		
		DoctorSpeciality doctorSpeciality2= new DoctorSpeciality(101,2);
		DoctorSpeciality doctorSpeciality3= new DoctorSpeciality(1,101,2);

		assertEquals(101, doctorSpeciality.getDoctorID());
		assertEquals(2, doctorSpeciality.getSpecialityID());
		assertEquals(100, doctorSpeciality.getId());

	}
	
	

	
	
}
