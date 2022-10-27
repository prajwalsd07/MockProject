package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DiagnosticDTOTest {
	@Test
	void testDiagnosticDTO() {
		DiagnosticDTO diagnosticDTO = new DiagnosticDTO();
		diagnosticDTO.setDiagnosticID(1);
		diagnosticDTO.setDiagnosticName("Biopsy");

		assertEquals(1, diagnosticDTO.getDiagnosticID());
		assertEquals("Biopsy", diagnosticDTO.getDiagnosticName());

	}

}