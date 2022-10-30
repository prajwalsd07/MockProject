package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.PatientDiagnostic;

class PatientDiagnosticDTOTest {

	@Test
	void testPtientDiagnosticDTO() {
		PatientDiagnosticDTO patientDiagnostic = new PatientDiagnosticDTO();
		patientDiagnostic.setDiagnosticID(3);
		patientDiagnostic.setPatientID(201);
		patientDiagnostic.setId(1);
		PatientDiagnosticDTO patientDiagnostic4 = new PatientDiagnosticDTO(3,201);
		PatientDiagnosticDTO patientDiagnostic2 = new PatientDiagnosticDTO(1,3,201);
		PatientDiagnosticDTO patientDiagnostic3 = new PatientDiagnosticDTO(1,3,201);

		assertEquals(1, patientDiagnostic2.getId());
		assertEquals(201, patientDiagnostic.getPatientID());
		assertEquals(201, patientDiagnostic3.getDiagnosticID());
	}

}