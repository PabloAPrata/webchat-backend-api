package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByPsychologist_User_UserId(UUID psychologistUserId);
}
