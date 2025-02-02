package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleId id; // Chave composta embutida

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "fk_role_id", referencedColumnName = "id")
    private Role role;
}

