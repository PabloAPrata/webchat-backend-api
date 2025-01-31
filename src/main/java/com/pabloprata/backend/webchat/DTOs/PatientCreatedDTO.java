package com.pabloprata.backend.webchat.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientCreatedDTO(
        UUID id,

        String firstName,

        String lastName,

        String phoneNumber,

        String email,

        LocalDateTime createdAt
) {
}
