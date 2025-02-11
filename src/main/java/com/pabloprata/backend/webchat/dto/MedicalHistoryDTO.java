package com.pabloprata.backend.webchat.dto;

public record MedicalHistoryDTO(
        String patientOccupation,
        String patientMarital,
        String patientReligion,
        String patientEducation,
        ParentInfoDTO father,
        ParentInfoDTO mother
) {
}
