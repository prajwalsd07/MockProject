package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientDiagnosticDTOTest {

	@Test
	void testPtientDiagnosticDTO() {
		PatientDiagnosticDTO patientDiagnosticDTO = new PatientDiagnosticDTO();
		patientDiagnosticDTO.setDiagnosticID(3);
		patientDiagnosticDTO.setPatientID(201);

		assertEquals(3, patientDiagnosticDTO.getDiagnosticID());
		assertEquals(201, patientDiagnosticDTO.getPatientID());

	}

}