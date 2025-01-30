package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class RolePermissionId implements Serializable {

    @Column(name = "fk_role_id")
    private Integer roleId;

    @Column(name = "fk_permission_id")
    private Integer permissionId;
}

