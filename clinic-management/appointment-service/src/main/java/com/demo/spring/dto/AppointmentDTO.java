package com.demo.spring.dto;

public class AppointmentDTO {

	private Integer appointmentID;

	private Integer doctorID;

	private Integer patientID;

	private String date;

	public AppointmentDTO() {

	}

	public AppointmentDTO(Integer appointmentID, Integer doctorID, Integer patientID, String date) {
		this.appointmentID = appointmentID;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.date = date;
	}

	public AppointmentDTO(Integer doctorID, Integer patientID, String date) {
		super();
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.date = date;
	}

	public Integer getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(Integer appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Integer getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public Integer getPatientID() {
		return patientID;
	}

	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
