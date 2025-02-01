package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByPsychologist_User_UserId(UUID psychologistUserId, Pageable pagination);

    Optional<Patient> findByUser_UserId(UUID userId);
}
