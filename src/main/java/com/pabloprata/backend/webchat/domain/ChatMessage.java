package com.pabloprata.backend.webchat.domain;

import com.pabloprata.backend.webchat.domain.enums.TypeMessageEnum;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private Integer chatMessageId;

    @ManyToOne
    @JoinColumn(name = "fk_meeting_id", nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_message", nullable = false)
    private TypeMessageEnum typeMessage = TypeMessageEnum.TEXT;

    @Column(name = "file_uri")
    private String fileUri;
}
