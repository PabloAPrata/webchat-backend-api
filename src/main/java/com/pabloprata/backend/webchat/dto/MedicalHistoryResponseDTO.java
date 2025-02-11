package com.pabloprata.backend.webchat.dto;

import java.util.UUID;

public record MedicalHistoryResponseDTO(
        UUID userId,
        String patientOccupation,
        String patientMarital,
        String patientReligion,
        String patientEducation,
        ParentInfoDTO father,
        ParentInfoDTO mother
) {
}
