package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.City;
import com.pabloprata.backend.webchat.domain.State;
import com.pabloprata.backend.webchat.dto.StateDTO;
import com.pabloprata.backend.webchat.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    
    private final CityRepository cityRepository;
    private final StateService stateService;

    public City getOrCreateCity(String cityName, StateDTO stateDto, String countryName) {

        if (cityName == null || cityName.isBlank()) {
            cityName = "Indefinido";
        }

        City existingCity = cityRepository.findByName(cityName);

        if (existingCity != null) {
            return existingCity;
        }

        // Pegar ESTADO
        State state = stateService.getOrCreateState(stateDto, countryName);

        City newCity = new City(cityName, state);
        newCity = cityRepository.save(newCity);
        return newCity;
    }
}
