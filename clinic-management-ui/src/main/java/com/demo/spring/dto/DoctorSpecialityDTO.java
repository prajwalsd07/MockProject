package com.demo.spring.dto;

public class DoctorSpecialityDTO {

	private int id;
	private Integer doctorID;
	private Integer specialityID;

	public DoctorSpecialityDTO() {

	}

	public DoctorSpecialityDTO(Integer doctorID, Integer specialityID) {
		this.doctorID = doctorID;
		this.specialityID = specialityID;
	}


	public int getId() {
		return id;
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
