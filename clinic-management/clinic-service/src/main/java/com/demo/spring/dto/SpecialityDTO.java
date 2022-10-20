package com.demo.spring.dto;

public class SpecialityDTO {

	
	private Integer specialityID;
	private String specialityName;

	public SpecialityDTO() {
		
	}

	public SpecialityDTO(Integer specialityID, String specialityName) {
	
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
