package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.TypeNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeNotificationRepository extends JpaRepository<TypeNotification, Long> {
}
