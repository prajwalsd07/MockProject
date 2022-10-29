package com.demo.spring.dto;

public class DoctorDTO {

	private Integer doctorID;
	private String firstName;

	private String lastName;

	private String email;

	public DoctorDTO() {

	}

	public DoctorDTO(Integer doctorID, String firstName, String lastName, String email) {
		this.doctorID = doctorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Integer getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(Integer doctorID) {
		this.doctorID = doctorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
