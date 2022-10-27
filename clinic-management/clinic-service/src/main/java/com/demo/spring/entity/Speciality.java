package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SPECIALITY")
public class Speciality {

	@Id
	@Column(name="SPECIALITY_ID")
	private Integer specialityID;
	@Column(name="SPECIALITY_NAME")
	private String specialityName;

	public Speciality() {
		
	}

	public Speciality(Integer specialityID, String specialityName) {
	
		this.specialityID = specialityID;
		this.specialityName = specialityName;
	}

	public Integer getSpecialityID() {
		return specialityID;
	}

	public void setSpecialityID(Integer specialityID) {
		this.specialityID = specialityID;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

}
