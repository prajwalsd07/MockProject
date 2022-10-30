package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.Speciality;

class SpecialityDTOTest {

	@Test
	void testSpecialityDTO() {
		SpecialityDTO speciality = new SpecialityDTO();
		
		speciality.setSpecialityID(1);
		speciality.setSpecialityName("Cardiology");

		assertEquals(1, speciality.getSpecialityID());
		assertEquals("Cardiology", speciality.getSpecialityName());
		Speciality speciality2 = new Speciality(1,"test");


	}

}