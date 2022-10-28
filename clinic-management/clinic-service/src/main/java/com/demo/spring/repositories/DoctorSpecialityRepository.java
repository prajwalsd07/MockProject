package com.demo.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.DoctorSpeciality;

public interface DoctorSpecialityRepository extends JpaRepository<DoctorSpeciality, Integer> {

	@Query("select d from DoctorSpeciality d where d.specialityID=:specialityID")
	public List<DoctorSpeciality> listDoctorInSpeciality(Integer specialityID);
	
	@Query("select d from DoctorSpeciality d where d.doctorID=:doctorID and d.specialityID=:specialityId")
	public List<DoctorSpeciality> findByDoctorIdAndSpecialityId(Integer doctorID,Integer specialityId);
	
	

}
