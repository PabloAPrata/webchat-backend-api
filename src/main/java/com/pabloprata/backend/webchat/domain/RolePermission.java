package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {

    @EmbeddedId
    private RolePermissionId id;  // Chave composta embutida

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "fk_role_id", referencedColumnName = "role_id")
    private Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "fk_permission_id", referencedColumnName = "permission_id")
    private Permission permission;
}

