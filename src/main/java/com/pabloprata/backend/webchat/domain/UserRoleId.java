package com.pabloprata.backend.webchat.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserRoleId implements Serializable {

    private UUID fkUserId;
    private Integer fkRoleId;

}

