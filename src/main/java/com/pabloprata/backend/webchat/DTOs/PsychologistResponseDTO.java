package com.pabloprata.backend.webchat.DTOs;

import java.time.LocalDateTime;
import java.util.UUID;

public record PsychologistResponseDTO(
        UUID id,

        String firstName,

        String lastName,

        String crp,

        String phoneNumber,

        String email,

        LocalDateTime createdAt
)
{}
