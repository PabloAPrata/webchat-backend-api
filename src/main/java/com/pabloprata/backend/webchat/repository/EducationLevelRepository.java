package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
}
