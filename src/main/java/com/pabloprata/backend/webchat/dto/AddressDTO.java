package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(

        @NotBlank(message = "{street.name.mandatory}")
        String street,

        @NotNull(message = "{house.number.mandatory}")
        String number,

        String complement,

        @NotBlank(message = "{district.name.mandatory}")
        String district,

        @NotBlank(message = "{zip.code.mandatory}")
        String zip_code,

        @NotBlank(message = "{city.name.mandatory}")
        String city,

        @NotBlank(message = "{state.mandatory}")
        StateDTO state,

        @NotBlank(message = "{country.name.mandatory}")
        String country


) {
}
