package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StateDTO(

        @NotBlank(message = "{state.name.mandatory}")
        String name,

        @NotBlank(message = "{state.uf.mandatory}")
        @Pattern(regexp = "^[A-Z]{2}$", message = "state.uf.pattern")
        String UF
) {
}
