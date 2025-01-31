package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
