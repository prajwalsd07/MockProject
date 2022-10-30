package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.DiagnosticDTO;

class DiagnosticTest {
	@Test
	void testDiagnostic() {
		Diagnostic diagnostic = new Diagnostic();
		diagnostic.setDiagnosticId(1);
		diagnostic.setDiagnosticName("Biopsy");
		
		Diagnostic diagnosticDTO2 = new Diagnostic(1,"Biopsy");

		assertEquals(1, diagnostic.getDiagnosticID());
		assertEquals("Biopsy", diagnostic.getDiagnosticName());

	}

}
