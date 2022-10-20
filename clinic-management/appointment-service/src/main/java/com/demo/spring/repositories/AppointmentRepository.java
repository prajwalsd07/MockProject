package com.demo.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	@Query("select a from Appointment a where a.date=:date and a.doctorID=:doctorID")
	public List<Appointment> findAllByDate(Integer doctorID,String date);
}
