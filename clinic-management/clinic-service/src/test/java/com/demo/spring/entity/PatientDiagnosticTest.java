package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.PatientDiagnosticDTO;

class PatientDiagnosticTest {

	@Test
	void testPatientDiagnostic() {
		PatientDiagnostic patientDiagnostic = new PatientDiagnostic();
		patientDiagnostic.setDiagnosticID(3);
		patientDiagnostic.setPatientID(201);
		patientDiagnostic.setId(1);
		PatientDiagnostic patientDiagnostic4 = new PatientDiagnostic(3,201);
		PatientDiagnostic patientDiagnostic2 = new PatientDiagnostic(1,3,201);
		PatientDiagnostic patientDiagnostic3 = new PatientDiagnostic(1,3,201);

		assertEquals(1, patientDiagnostic2.getId());
		assertEquals(201, patientDiagnostic.getPatientID());
		assertEquals(201, patientDiagnostic3.getDiagnosticID());

	}

}
