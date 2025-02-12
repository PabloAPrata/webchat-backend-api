package com.pabloprata.backend.webchat.dto;

public record AddressResponseDTO(

        Long id,

        String street,

        String number,

        String complement,

        String district,

        String zipCode,

        String city,

        StateDTO state,

        String country
) {
}
