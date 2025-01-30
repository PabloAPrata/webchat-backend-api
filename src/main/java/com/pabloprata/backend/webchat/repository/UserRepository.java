package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
