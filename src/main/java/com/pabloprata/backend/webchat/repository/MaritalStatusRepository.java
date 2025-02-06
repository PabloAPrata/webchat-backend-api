package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {
}
