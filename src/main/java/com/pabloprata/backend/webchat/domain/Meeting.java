package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "meetings")
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "type_meeting_id", nullable = false)
    private TypeMeeting typeMeeting;

    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation;

    @ManyToOne
    @JoinColumn(name = "fk_psychologist_id", nullable = false)
    private Psychologist psychologist;

    @Column(name = "uri_access", unique = true, nullable = false)
    private String uriAccess;

    @ManyToOne
    @JoinColumn(name = "status_meeting_id", nullable = false)
    private StatusMeeting statusMeeting;

    @Column(name = "summary")
    private String summary;

    @Column(name = "recording_consent", nullable = false)
    private Boolean recordingConsent = false;

    @Column(name = "uri_record_file")
    private String uriRecordFile;

    @Column(name = "uri_image")
    private String uriImage;
}
