package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SpecialityDTOTest {

	@Test
	void testSpecialityDTO() {
		SpecialityDTO specialityDTO = new SpecialityDTO();
		specialityDTO.setSpecialityID(1);
		specialityDTO.setSpecialityName("Cardiology");

		assertEquals(1, specialityDTO.getSpecialityID());
		assertEquals("Cardiology", specialityDTO.getSpecialityName());

	}

}