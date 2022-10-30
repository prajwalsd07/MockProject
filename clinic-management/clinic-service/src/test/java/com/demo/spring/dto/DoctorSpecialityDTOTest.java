package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.DoctorSpeciality;

class DoctorSpecialityDTOTest {

	@Test
	void testDiagnosticDTO() {
		DoctorSpecialityDTO doctorSpeciality = new DoctorSpecialityDTO();
		doctorSpeciality.setDoctorID(101);
		doctorSpeciality.setSpecialityID(2);
		
		DoctorSpecialityDTO doctorSpeciality2= new DoctorSpecialityDTO(101,2);
		

		assertEquals(101, doctorSpeciality.getDoctorID());
		assertEquals(2, doctorSpeciality.getSpecialityID());
		assertEquals(0, doctorSpeciality.getId());

	}

}