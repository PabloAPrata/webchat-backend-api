package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.TypeMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMessageRepository extends JpaRepository<TypeMessage, Long> {
}
