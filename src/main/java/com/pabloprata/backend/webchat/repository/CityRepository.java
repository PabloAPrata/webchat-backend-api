package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
