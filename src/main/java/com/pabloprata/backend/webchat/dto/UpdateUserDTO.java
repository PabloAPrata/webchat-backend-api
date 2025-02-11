package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.UUID;

public record UpdateUserDTO(

        UUID id,

        @NotBlank(message = "{name.mandatory}")
        @Size(min = 2, max = 50, message = "{name.incorrect.pattern}")
        String name,

        String image,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{date.incorrect.pattern}")
        String dateBirth,

        @NotBlank(message = "{phone.mandatory}")
        String phoneNumber,

        @NotBlank(message = "{cpf.mandatory}")
        @CPF
        String cpf,

        @NotBlank(message = "{email.mandatory}")
        @Email(message = "{email.incorrect.pattern}")
        String email
) {}
