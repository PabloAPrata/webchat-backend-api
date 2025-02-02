package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Administrator, UUID> {

    UserDetails findByLogin(String login);
}
