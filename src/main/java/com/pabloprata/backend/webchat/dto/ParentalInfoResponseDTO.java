package com.pabloprata.backend.webchat.dto;

public record ParentalInfoResponseDTO(
        String parentName,
        Integer parentAge,
        String parentEducation,
        String parentOccupation,
        String parentNotes
) {
}
