package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.Country;
import com.pabloprata.backend.webchat.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country getOrCreateCountry(String countryName) {

        Country existingCountry = countryRepository.findByName(countryName);

        if (existingCountry != null) {
            return existingCountry;
        }

        Country newCountry = new Country(countryName);
        newCountry = countryRepository.save(newCountry);

        return newCountry;
    }
}
