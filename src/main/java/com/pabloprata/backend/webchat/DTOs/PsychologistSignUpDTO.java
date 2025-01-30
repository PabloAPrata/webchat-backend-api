package com.pabloprata.backend.webchat.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PsychologistSignUpDTO(
        @NotBlank(message = "O nome não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O nome precisa ter entre 3 e 50 caracteres.")
        String firstName,

        @NotBlank(message = "O nome do meio não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O nome do meio precisa ter entre 2 e 50 caracteres.")
        String middleName,

        @NotBlank(message = "O último nome não pode estar vazio.")
        @Size(min = 3, max = 50, message = "O último nome precisa ter entre 3 e 50 caracteres.")
        String lastName,

        @NotBlank(message = "O CPF não pode estar vazio.")
        @CPF
        String cpf,

        @NotBlank(message = "O CRP não pode estar vazio.")
        String crp,

        @NotBlank(message = "O nome não pode estar vazio.")
        String phoneNumber,

        @NotBlank(message = "Email é requerido.")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "O gênero não pode estar vazio.")
        @Size(min = 1, max = 1, message = "Gender must be 1 character.")
        String gender,

        @NotBlank(message = "A senha não pode estar vazia.")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$",
                message = "A senha deve conter pelo menos 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial")
        String password)
{}
