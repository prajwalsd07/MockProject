package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.Doctor;

class DoctorDTOTest {

	@Test
	void testDoctorDTO() {
		DoctorDTO doctor = new DoctorDTO();
		doctor.setFirstName("suraj");
		doctor.setLastName("mogali");
		doctor.setEmail("surajmogali@gmail.com");
		doctor.setDoctorID(101);
		
		DoctorDTO doctorDTO2 = new DoctorDTO(101,"suraj","mogali","surajmogali@gmail.com");

		assertEquals("suraj", doctor.getFirstName());
		assertEquals("mogali", doctor.getLastName());
		assertEquals("surajmogali@gmail.com", doctor.getEmail());
		assertEquals(101, doctor.getDoctorID());

	}
}