package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    public User(String name, String cpf, String telephone, String email, LocalDate dateBirth, Gender gender) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.dateBirth = dateBirth;
        this.gender = gender;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato xxx.xxx.xxx-xx")
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;

    @Pattern(regexp = "^\\+\\d{1,3}\\s?\\(?\\d{1,4}\\)?\\s?\\d{4,5}-?\\d{4}$", message = "Telefone inválido.")
    @Column(unique = true)
    private String telephone;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    @NotNull(message = "Gênero é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "fk_gender_id", nullable = false)
    private Gender gender;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}
