package com.pabloprata.backend.webchat.dto;

public record ParentInfoDTO(
        String parentName,
        Integer parentAge,
        Integer parentEducation,
        Integer parentOccupation,
        String parentNotes
) {
}
