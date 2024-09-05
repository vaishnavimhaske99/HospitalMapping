package com.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Pharmacist;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer> {
}
