package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "type_notification")
public class TypeNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_notification_id")
    private Long id;

    @Column(name = "name", length = 20, unique = true, nullable = false)
    private String name;
}