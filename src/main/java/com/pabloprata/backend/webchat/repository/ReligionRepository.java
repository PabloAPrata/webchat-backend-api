package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {
}
