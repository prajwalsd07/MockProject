package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DOCTOR_SPECIALITY")
public class DoctorSpeciality{

	@Id
    @SequenceGenerator(sequenceName = "DoctorSpeciality_sequence",initialValue = 6, name = "DoctorSpeciality_Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "DoctorSpeciality_Id")
	
    @Column(name="ID")
    private int id;
	
	@Column(name = "DOCTOR_ID")
	private Integer doctorID;
	
	@Column(name = "SPECIALITY_ID")
	private Integer specialityID;

	public DoctorSpeciality(int id, Integer doctorID, Integer specialityID) {
	
		this.id = id;
		this.doctorID = doctorID;
		this.specialityID = specialityID;
	}

	public DoctorSpeciality() {
		
	}

	public DoctorSpeciality(Integer doctorID, Integer specialityID) {
		this.doctorID = doctorID;
		this.specialityID = specialityID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public Integer getSpecialityID() {
		return specialityID;
	}

	public void setSpecialityID(Integer specialityID) {
		this.specialityID = specialityID;
	}

}
