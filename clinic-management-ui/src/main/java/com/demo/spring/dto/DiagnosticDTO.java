package com.demo.spring.dto;

public class DiagnosticDTO {

	private Integer diagnosticID;
	private String diagnosticName;

	public DiagnosticDTO() {

	}

	public DiagnosticDTO(Integer diagnosticID, String diagnosticName) {
		super();
		this.diagnosticID = diagnosticID;
		this.diagnosticName = diagnosticName;
	}

	public Integer getDiagnosticID() {
		return diagnosticID;
	}

	public void setDiagnosticID(Integer diagnosticID) {
		this.diagnosticID = diagnosticID;
	}

	public String getDiagnosticName() {
		return diagnosticName;
	}

	public void setDiagnosticName(String diagnosticName) {
		this.diagnosticName = diagnosticName;
	}
	

}