package com.pabloprata.backend.webchat.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MeetingParticipantId implements Serializable {

    private Long meetingId;
    private UUID userId;

}
