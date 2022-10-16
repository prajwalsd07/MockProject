package com.demo.spring.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
@Column(name="APPOINTMENT_ID")
	private Integer appointmentID;
	@Column(name="DOCTOR_ID")
	private Integer doctorID;
	@Column(name="PATIENT_ID")
	private Integer patientID;
	@Column(name="DATE")
	private Date date;

	public Appointment() {

	}

	public Appointment(Integer appointmentID, Integer doctorID, Integer patientID, Date date) {
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
