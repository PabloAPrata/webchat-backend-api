package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record PatientCreatedDTO(
        UUID id,

        String name,

        String phoneNumber,

        String email
) {
}
