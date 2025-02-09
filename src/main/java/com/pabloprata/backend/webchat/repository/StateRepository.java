package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
