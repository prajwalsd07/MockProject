package com.demo.spring.dto;

import java.sql.Date;

public class AppointmentDTO {

	private Integer appointmentID;

	private Integer doctorID;

	private Integer patientID;

	private Date date;

	public AppointmentDTO() {

	}

	public AppointmentDTO(Integer appointmentID, Integer doctorID, Integer patientID, Date date) {
		this.appointmentID = appointmentID;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
