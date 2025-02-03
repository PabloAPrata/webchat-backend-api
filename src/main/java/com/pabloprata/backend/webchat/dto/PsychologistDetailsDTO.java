package com.pabloprata.backend.webchat.dto;

public record PsychologistDetailsDTO(
        String name,
        String email,
        String telephone,
        String crp,
        String gender,
        String street,
        String number,
        String complement,
        String district,
        String zipCode,
        String city,
        String state,
        String country
) {
}
