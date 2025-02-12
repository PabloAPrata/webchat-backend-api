package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.Occupation;
import com.pabloprata.backend.webchat.repository.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OccupationService {

    private final OccupationRepository occupationRepository;

    public Integer getOrCreateOccupation(String occupationName) {

        if (occupationName == null || occupationName.isBlank()) {
            occupationName = "Indefinido";
        }

        Occupation existingOccupation = occupationRepository.findByName(occupationName);

        if (existingOccupation != null) {
            return existingOccupation.getId();
        }

        Occupation newOccupation = new Occupation(occupationName);
        newOccupation = occupationRepository.save(newOccupation);
        return newOccupation.getId();
    }
}
