package com.demo.spring.dto;

public class PatientDiagnosticDTO {

	private Integer id;

	private Integer patientID;

	private Integer diagnosticID;

	public PatientDiagnosticDTO() {
	}

	public PatientDiagnosticDTO(Integer patientID, Integer diagnosticID) {
		super();
		this.patientID = patientID;
		this.diagnosticID = diagnosticID;
	}

	public PatientDiagnosticDTO(Integer id, Integer patientID, Integer diagnosticID) {
		super();
		this.id = id;
		this.patientID = patientID;
		this.diagnosticID = diagnosticID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public Integer getDiagnosticID() {
		return diagnosticID;
	}

	public void setDiagnosticID(Integer diagnosticID) {
		this.diagnosticID = diagnosticID;
	}

}
