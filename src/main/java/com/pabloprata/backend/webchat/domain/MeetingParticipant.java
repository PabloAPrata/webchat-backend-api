package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_participants")
@Data
@IdClass(MeetingParticipantId.class)
public class MeetingParticipant {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_meeting_id", nullable = false)
    private Meeting meeting;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;
}
