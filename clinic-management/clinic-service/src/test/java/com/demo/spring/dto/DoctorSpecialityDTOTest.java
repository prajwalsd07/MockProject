package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoctorSpecialityDTOTest {

	@Test
	void testDiagnosticDTO() {
		DoctorSpecialityDTO doctorSpecialityDTO = new DoctorSpecialityDTO();
		doctorSpecialityDTO.setDoctorID(101);
		doctorSpecialityDTO.setSpecialityID(2);

		assertEquals(101, doctorSpecialityDTO.getDoctorID());
		assertEquals(2, doctorSpecialityDTO.getSpecialityID());

	}

}