package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record PatientCreatedDTO(
        UUID id,

        String firstName,

        String lastName,

        String phoneNumber,

        String email
) {
}
