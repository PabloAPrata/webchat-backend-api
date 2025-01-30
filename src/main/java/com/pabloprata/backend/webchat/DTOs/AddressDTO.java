package com.pabloprata.backend.webchat.DTOs;

import java.util.UUID;

public record AddressDTO(
        UUID userId,
        String street,
        Integer number,
        String complement,
        String district,
        String zip_code,
        String city,
        String state,
        String country
) {}
