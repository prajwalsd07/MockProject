package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SpecialityTest {

	@Test
	void testSpeciality() {
		Speciality speciality = new Speciality();
	
		speciality.setSpecialityID(1);
		speciality.setSpecialityName("Cardiology");

		assertEquals(1, speciality.getSpecialityID());
		assertEquals("Cardiology", speciality.getSpecialityName());
		Speciality speciality2 = new Speciality(1,"test");

	}

}