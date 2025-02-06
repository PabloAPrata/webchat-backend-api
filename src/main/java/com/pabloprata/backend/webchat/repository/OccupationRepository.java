package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Long> {
}
