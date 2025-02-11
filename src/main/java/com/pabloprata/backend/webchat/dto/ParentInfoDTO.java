package com.pabloprata.backend.webchat.dto;

public record ParentInfoDTO(
        String parentName,
        Integer parentAge,
        String parentEducation,
        String parentOccupation,
        String parentNotes
) {
}
