package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserRoleId implements Serializable {

    @Column(name = "fk_user_id")
    private UUID userId;

    @Column(name = "fk_role_id")
    private Integer roleId;
}

