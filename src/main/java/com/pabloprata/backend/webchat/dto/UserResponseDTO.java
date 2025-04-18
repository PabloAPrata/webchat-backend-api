package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,

        String name,

        String cpf,

        String phoneNumber,

        String email,

        String patientStatus,

        String image,

        String dateBirth
) {
}
