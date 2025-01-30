package com.pabloprata.backend.webchat.DTOs;

import java.time.LocalDate;
import java.util.UUID;

public record PsychologistResponseDTO(
        UUID id,

        String firstName,

        String middleName,

        String lastName,

        String crp,

        String phoneNumber,

        String email,

        LocalDate createdAt
)
{}
