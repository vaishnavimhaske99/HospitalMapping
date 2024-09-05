package com.webapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Reception;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Integer> {
}