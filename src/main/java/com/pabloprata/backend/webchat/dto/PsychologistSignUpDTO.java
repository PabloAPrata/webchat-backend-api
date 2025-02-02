package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record PsychologistSignUpDTO(
        @NotBlank(message = "{firstName.mandatory}")
        @Size(min = 2, max = 50, message = "{firstName.incorrect.pattern}")
        String firstName,

        @Size(min = 3, max = 50, message = "{middleName.mandatory}")
        String middleName,

        @NotBlank(message = "{lastName.mandatory}")
        @Size(min = 2, max = 50, message = "{lastName.incorrect.pattern}")
        String lastName,

        @NotBlank(message = "{cpf.mandatory}")
        @CPF
        String cpf,

        @NotBlank(message = "crp.mandatory")
        @Pattern(regexp = "^[A-Z]{2}/\\d{4,5}(-\\d{1,2})?$",
                        message = "crp.incorrect.pattern")
        String crp,

        @NotBlank(message = "{phone.mandatory}")
        String phoneNumber,

        @NotBlank(message = "{email.mandatory}")
        @Email(message = "email.incorrect.pattern")
        String email,

        Date dateBirth,

        @NotNull(message = "{gender.mandatory}")
        Integer genderId,

        @NotBlank(message = "{password.mandatory}")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",
                message = "{password.incorrect.pattern}")
        String password
) {}
