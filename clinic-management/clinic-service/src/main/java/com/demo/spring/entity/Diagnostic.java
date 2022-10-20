package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIAGNOSTIC")
public class Diagnostic {

	@Id
	@Column(name = "DIAGNOSTIC_ID")
	private Integer diagnosticID;
	@Column(name = "DIAGNOSTIC_NAME")
	private String diagnosticName;

	public Diagnostic() {

	}

	public Diagnostic(Integer diagnosticID, String diagnosticName) {
		super();
		this.diagnosticID = diagnosticID;
		this.diagnosticName = diagnosticName;
	}

	public Integer getDiagnosticID() {
		return diagnosticID;
	}

	public void setDiagnosticId(Integer diagnosticID) {
		this.diagnosticID = diagnosticID;
	}

	public String getDiagnosticName() {
		return diagnosticName;
	}

	public void setDiagnosticName(String diagnosticName) {
		this.diagnosticName = diagnosticName;
	}

}
