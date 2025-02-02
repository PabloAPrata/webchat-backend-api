package com.pabloprata.backend.webchat.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import java.util.Date;
import java.util.UUID;


public record PatientSignUpDTO(

        @NotNull(message = "É necessário informar a qual psicólogo pertence este usuário")
        UUID PsychologistId,

        @NotBlank(message = "O nome não pode estar vazio.")
        @Size(min = 2, max = 50, message = "O nome precisa ter entre 2 e 50 caracteres.")
        String firstName,

        @Size(min = 3, max = 50, message = "O nome do meio precisa ter entre 2 e 50 caracteres.")
        String middleName,

        @NotBlank(message = "O último nome não pode estar vazio.")
        @Size(min = 2, max = 50, message = "O último nome precisa ter entre 2 e 50 caracteres.")
        String lastName,

        @NotBlank(message = "O CPF não pode estar vazio.")
        @CPF
        String cpf,

        @NotBlank(message = "O nome não pode estar vazio.")
        String phoneNumber,

        @NotBlank(message = "Email é requerido.")
        @Email(message = "Invalid email format")
        String email,

        Date dateBirth,

        @NotNull(message = "O gênero não pode estar vazio.")
        Integer genderId
) {
}
