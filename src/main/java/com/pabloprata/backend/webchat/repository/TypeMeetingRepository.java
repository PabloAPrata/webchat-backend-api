package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.TypeMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMeetingRepository extends JpaRepository<TypeMeeting, Long> {
}
