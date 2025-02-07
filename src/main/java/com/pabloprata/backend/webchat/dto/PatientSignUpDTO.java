package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.util.UUID;


public record PatientSignUpDTO(

        @NotNull(message = "{psychologistId.mandatory}")
        UUID PsychologistId,

        @NotBlank(message = "{firstName.mandatory}")
        @Size(min = 2, max = 50, message = "{firstName.incorrect.pattern}")
        String firstName,

        @Size(min = 3, max = 50, message = "{middleName.incorrect.pattern}")
        String middleName,

        @NotBlank(message = "{lastName.mandatory}")
        @Size(min = 2, max = 50, message = "{lastName.incorrect.pattern}")
        String lastName,

        @NotBlank(message = "{cpf.mandatory}")
        @CPF
        String cpf,

        @NotBlank(message = "{phone.mandatory}")
        String phoneNumber,

        @NotBlank(message = "{email.mandatory}")
        @Email(message = "{email.incorrect.pattern}")
        String email,

        String dateBirth,

        @NotNull(message = "{gender.mandatory}")
        Integer genderId
) {
}
