package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {

	
	@Id
    @SequenceGenerator(sequenceName = "Appointment_sequence",initialValue = 100, name = "Appointment_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Appointment_Id")
	
	@Column(name = "APPOINTMENT_ID")
	private Integer appointmentID;
	@Column(name = "DOCTOR_ID")
	private Integer doctorID;
	@Column(name = "PATIENT_ID")
	private Integer patientID;
	@Column(name = "DATE")
	private String date;

	public Appointment() {

	}

	public Appointment(Integer doctorID, Integer patientID, String date) {
		super();
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.date = date;
	}

	public Appointment(Integer appointmentID, Integer doctorID, Integer patientID, String date) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
