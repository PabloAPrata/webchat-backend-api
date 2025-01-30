package com.pabloprata.backend.webchat.domain;

import com.pabloprata.backend.webchat.domain.enums.StatusMeetingEnum;
import com.pabloprata.backend.webchat.domain.enums.TypeMeetingEnum;
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
    private Integer meetingId;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_meeting", nullable = false)
    private TypeMeetingEnum typeMeeting;

    @Column(name = "date_time", nullable = false)
    private Timestamp dateTime;

    @Column(name = "date_creation", nullable = false)
    private Timestamp dateCreation;

    @ManyToOne
    @JoinColumn(name = "fk_psychologist_id", nullable = false)
    private Psychologist psychologist;

    @Column(name = "uri_access", unique = true, nullable = false)
    private String uriAccess;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_meeting", nullable = false)
    private StatusMeetingEnum statusMeeting = StatusMeetingEnum.AGENDADA;

    @Column(name = "summary")
    private String summary;

    @Column(name = "recording_consent", nullable = false)
    private Boolean recordingConsent = false;

    @Column(name = "uri_record_file")
    private String uriRecordFile;

    @Column(name = "uri_image")
    private String uriImage;
}
