package com.pabloprata.backend.webchat.DTOs;

import jakarta.validation.constraints.NotBlank;

public record PatientStatusUpdateDTO(
        @NotBlank(message = "Informe o status")
        String patientStatus
) {
}
