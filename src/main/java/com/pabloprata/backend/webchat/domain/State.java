package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "states_id")
    private Integer stateId;

    @NotBlank(message = "Nome do estado é obrigatório.")
    @Size(max = 100, message = "Nome do estado pode ter no máximo 100 caracteres.")
    private String name;

    @NotBlank(message = "UF do estado é obrigatório.")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres.")
    private String uf;

    @ManyToOne
    @JoinColumn(name = "fk_country_id", referencedColumnName = "country_id", nullable = false)
    private Integer fkCountryId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
