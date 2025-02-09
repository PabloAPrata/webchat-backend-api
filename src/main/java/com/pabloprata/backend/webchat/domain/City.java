package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cities")
public class City {

    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da cidade é obrigatório.")
    @Size(max = 100, message = "Nome da cidade pode ter no máximo 100 caracteres.")
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_state_id", referencedColumnName = "id", nullable = false)
    private State state;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
