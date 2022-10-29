package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_DIAGNOSTIC")
public class PatientDiagnostic {
	
	@Id
    @SequenceGenerator(sequenceName = "PatientDiagnostic_sequence",initialValue = 5, name = "PatientDiagnostic_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PatientDiagnostic_Id")
	
    @Column(name="ID")
    private Integer id;
	
	@Column(name = "DIAGNOSTIC_ID")
	private Integer patientID;
	@Column(name = "PATIENT_ID")
	private Integer diagnosticID;

	public PatientDiagnostic() {
	}

	public PatientDiagnostic(Integer patientID, Integer diagnosticID) {
		super();
		this.patientID = patientID;
		this.diagnosticID = diagnosticID;
	}

	public PatientDiagnostic(Integer id, Integer patientID, Integer diagnosticID) {
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
	}

}
