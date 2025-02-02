package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.NotBlank;

public record PatientStatusUpdateDTO(
        @NotBlank(message = "Informe o status do paciente.")
        String patientStatus
) {
}
