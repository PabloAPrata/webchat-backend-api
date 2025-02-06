package com.pabloprata.backend.webchat.dto;

public record MedicalHistoryDTO(
        Integer patientOccupation,
        Integer patientMarital,
        Integer patientReligion,
        Integer patientEducation,
        ParentInfoDTO father,
        ParentInfoDTO mother
) {
}
