package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.Country;
import com.pabloprata.backend.webchat.domain.State;
import com.pabloprata.backend.webchat.dto.StateDTO;
import com.pabloprata.backend.webchat.repository.CountryRepository;
import com.pabloprata.backend.webchat.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;
    private final CountryService countryService;
    private final CountryRepository countryRepository;

    public State getOrCreateState(StateDTO stateDto, String countryName) {

        stateDto = validateState(stateDto);

        State existingState = stateRepository.findByName(stateDto.name());

        if (existingState != null) {
            return existingState;
        }

        Country country = countryService.getOrCreateCountry(countryName);

        State newState = new State(stateDto.name(), stateDto.UF(), country);
        newState = stateRepository.save(newState);

        return newState;
    }

    private StateDTO validateState(StateDTO stateDto) {

        if (stateDto == null || stateDto.name() == null || stateDto.name().isBlank()) {
            stateDto = new StateDTO("Indefinido", "Nd");
        }

        if (stateDto.UF() == null || stateDto.UF().isBlank()) {
            stateDto = new StateDTO("Indefinido", "Nd");
        }

        return stateDto;
    }
}
