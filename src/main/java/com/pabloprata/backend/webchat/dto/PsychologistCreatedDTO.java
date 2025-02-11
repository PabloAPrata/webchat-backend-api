package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record PsychologistCreatedDTO(
        UUID id,

        String name,

        String crp,

        String phoneNumber,

        String email
)
{}
