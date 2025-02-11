package com.pabloprata.backend.webchat.dto;

public record AddressResponseDTO(

        Long id,

        String street,

        String number,

        String complement,

        String district,

        String zip_code,

        String city,

        StateDTO state,

        String country
) {
}
