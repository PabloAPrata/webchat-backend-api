package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting_participants")
@Data
public class MeetingParticipant {

    @EmbeddedId
    private MeetingParticipantId id;
}
