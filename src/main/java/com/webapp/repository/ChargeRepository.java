package com.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.model.Charges;

@Repository
public interface ChargeRepository extends JpaRepository<Charges, Integer> {
}