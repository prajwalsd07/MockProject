package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.DoctorDTO;

class DoctorTest {

	@Test
	void testDoctor() {
		Doctor doctor = new Doctor();
		doctor.setFirstName("suraj");
		doctor.setLastName("mogali");
		doctor.setEmail("surajmogali@gmail.com");
		doctor.setDoctorID(101);
		
		Doctor doctorDTO2 = new Doctor(101,"suraj","mogali","surajmogali@gmail.com");

		assertEquals("suraj", doctor.getFirstName());
		assertEquals("mogali", doctor.getLastName());
		assertEquals("surajmogali@gmail.com", doctor.getEmail());
		assertEquals(101, doctor.getDoctorID());

	}
}
