package com.pabloprata.backend.webchat.dto;

public record PatientDetailsDTO(
        String name,
        String email,
        String telephone,
        String gender,
        String patientStatus,
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
