package com.pabloprata.backend.webchat.dto;

public record MedicalHistoryResponseDTO(
        String patientOccupation,
        String patientMarital,
        String patientReligion,
        String patientEducation,
        ParentalInfoResponseDTO father,
        ParentalInfoResponseDTO mother
) {
}
