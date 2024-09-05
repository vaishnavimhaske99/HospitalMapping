package com.webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


}