package com.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Medicine;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}
