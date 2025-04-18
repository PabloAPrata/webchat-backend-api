package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
}
