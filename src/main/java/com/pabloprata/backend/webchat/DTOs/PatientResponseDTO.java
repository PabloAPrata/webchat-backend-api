package com.pabloprata.backend.webchat.DTOs;

import java.util.UUID;

public record PatientResponseDTO(
        UUID id,

        String name,

        String cpf,

        String phoneNumber,

        String email,

        String image,

        String dateBirth
) {
}
