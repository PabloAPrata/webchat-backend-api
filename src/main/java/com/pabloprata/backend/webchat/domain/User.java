package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID userId;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome é obrigatório.")
    private String name;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato xxx.xxx.xxx-xx")
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;

    @Pattern(regexp = "^\\+55\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$", message = "Telefone inválido.")
    @Column(unique = true)
    private String telephone;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gênero é obrigatório.")
    private Gender gender;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate created_at;

    public enum Gender {
        M, F, O
    }
}
