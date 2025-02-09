package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "meeting_participants")
public class MeetingParticipant {

    @EmbeddedId
    private MeetingParticipantId id;
}
