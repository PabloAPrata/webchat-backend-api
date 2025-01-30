package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "Rua é obrigatória.")
    @Size(max = 255, message = "Nome da rua pode ter no máximo 255 caracteres.")
    private String street;

    @NotBlank(message = "Número é obrigatório.")
    @Size(max = 10, message = "Número pode ter no máximo 10 caracteres.")
    private String number;

    @Size(max = 100, message = "Complemento pode ter no máximo 100 caracteres.")
    private String complement;

    @NotBlank(message = "Bairro é obrigatório.")
    @Size(max = 100, message = "Bairro pode ter no máximo 100 caracteres.")
    private String district;

    @NotBlank(message = "CEP é obrigatório.")
    @Size(max = 20, message = "CEP pode ter no máximo 20 caracteres.")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "fk_city_id", referencedColumnName = "city_id", nullable = false)
    private City city;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
