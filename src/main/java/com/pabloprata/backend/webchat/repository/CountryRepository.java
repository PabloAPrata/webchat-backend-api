package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
