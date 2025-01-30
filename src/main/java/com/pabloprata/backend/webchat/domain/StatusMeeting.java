package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "status_meeting")
public class StatusMeeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_meeting_id")
    private Long id;

    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;
}
