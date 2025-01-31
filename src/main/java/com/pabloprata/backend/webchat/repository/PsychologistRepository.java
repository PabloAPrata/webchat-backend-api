package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Optional<Psychologist> findByUser(User user);

    Optional<Psychologist> findByUserUserId(UUID userId);
}
