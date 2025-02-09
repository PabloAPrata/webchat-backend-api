package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_chat_message_id")
    private ChatMessage chatMessage;

    @ManyToOne
    @JoinColumn(name = "type_notification_id", nullable = false)
    private TypeNotification typeNotification;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "description")
    private String description;
}
