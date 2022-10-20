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
    @SequenceGenerator(sequenceName = "DoctorSpeciality_sequence",initialValue = 5, name = "DoctorSpeciality_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DoctorSpeciality_Id")
	
    @Column(name="ID")
    private Integer id;
	
	@Column(name = "DIAGNOSTIC_ID")
	private Integer PatientID;
	@Column(name = "PATIENT_ID")
	private Integer DiagnosticID;

	public PatientDiagnostic() {
	}

	public PatientDiagnostic(Integer patientID, Integer diagnosticID) {
		super();
		PatientID = patientID;
		DiagnosticID = diagnosticID;
	}

	public PatientDiagnostic(Integer id, Integer patientID, Integer diagnosticID) {
		super();
		this.id = id;
		PatientID = patientID;
		DiagnosticID = diagnosticID;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientID() {
		return PatientID;
	}

	public void setPatientID(Integer patientID) {
		PatientID = patientID;
	}

	public Integer getDiagnosticID() {
		return DiagnosticID;
	}

	public void setDiagnosticID(Integer diagnosticID) {
		DiagnosticID = diagnosticID;
	}

}
