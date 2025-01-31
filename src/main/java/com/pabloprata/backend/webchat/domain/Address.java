package com.pabloprata.backend.webchat.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", nullable = false)
    private User user;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false, length = 10)
    private String number;

    @Column(name = "complement", length = 100)
    private String complement;

    @Column(name = "district", nullable = false, length = 100)
    private String district;

    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "fk_city_id", nullable = false)
    private City city;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
