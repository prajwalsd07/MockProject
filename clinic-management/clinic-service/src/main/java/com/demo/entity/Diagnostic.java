package com.demo.entity;

public class Diagnostic {
	
	
private Integer diagnosticID;

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
