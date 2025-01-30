package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;
}
