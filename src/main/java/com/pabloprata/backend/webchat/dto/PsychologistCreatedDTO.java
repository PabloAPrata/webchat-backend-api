package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record PsychologistCreatedDTO(
        UUID id,

        String firstName,

        String lastName,

        String crp,

        String phoneNumber,

        String email
)
{}
