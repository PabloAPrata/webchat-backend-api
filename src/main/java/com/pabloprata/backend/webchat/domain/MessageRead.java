package com.pabloprata.backend.webchat.domain;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "message_read")
@Data
public class MessageRead {

    @EmbeddedId
    private MessageReadId id;

    @ManyToOne
    @JoinColumn(name = "fk_message_id", insertable = false, updatable = false)
    private ChatMessage chatMessage;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", insertable = false, updatable = false)
    private User user;

}
