package com.pabloprata.backend.webchat.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.UUID;

public record UpdateUserDTO(

        UUID id,

        @NotBlank(message = "O nome não pode estar vazio.")
        @Size(min = 2, max = 50, message = "O nome precisa ter entre 2 e 50 caracteres.")
        String name,

        String image,

        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        Date dateBirth,

        @NotBlank(message = "O telefone não pode estar vazio.")
        String phoneNumber,

        @NotBlank(message = "Email é requerido.")
        @Email(message = "Email no formato errado.")
        String email
) {}
