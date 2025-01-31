package com.pabloprata.backend.webchat.DTOs;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record PsychologistSignUpDTO(
        @NotBlank(message = "O primeiro nome não pode estar vazio.")
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

        @NotBlank(message = "O CRP não pode estar vazio.")
        @Pattern(regexp = "^[A-Z]{2}/\\d{4,5}(-\\d{1,2})?$",
                        message = "Digite um CRP válido.")
        String crp,

        @NotBlank(message = "O telefone não pode estar vazio.")
        String phoneNumber,

        @NotBlank(message = "Email é requerido.")
        @Email(message = "Email no formato errado.")
        String email,

        Date dateBirth,

        @NotNull(message = "O gênero não pode estar vazio.")
        Integer genderId,

        @NotBlank(message = "A senha não pode estar vazia.")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",
                message = "A senha deve conter pelo menos 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial")
        String password
) {}
