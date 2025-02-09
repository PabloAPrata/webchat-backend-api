package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.StatusMeeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusMeetingRepository extends JpaRepository<StatusMeeting, Long> {
}
