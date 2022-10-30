package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.Diagnostic;

class DiagnosticDTOTest {
	@Test
	void testDiagnosticDTO() {
		DiagnosticDTO diagnostic = new DiagnosticDTO();
		diagnostic.setDiagnosticID(1);
		diagnostic.setDiagnosticName("Biopsy");
		
		DiagnosticDTO diagnosticDTO2 = new DiagnosticDTO(1,"Biopsy");

		assertEquals(1, diagnostic.getDiagnosticID());
		assertEquals("Biopsy", diagnostic.getDiagnosticName());

	}

}