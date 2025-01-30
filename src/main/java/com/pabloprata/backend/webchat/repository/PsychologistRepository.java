package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {


}
