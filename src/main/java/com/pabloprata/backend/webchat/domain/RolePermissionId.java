package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RolePermissionId implements Serializable {

    private Integer fkRoleId;
    private Integer fkPermissionId;
}
