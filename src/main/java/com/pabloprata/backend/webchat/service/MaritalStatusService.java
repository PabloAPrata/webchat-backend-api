package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.MaritalStatus;
import com.pabloprata.backend.webchat.repository.MaritalStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaritalStatusService {
    private final MaritalStatusRepository maritalStatusRepository;

    public Integer getOrCreateMaritalStatus(String maritalStatusName) {

        if (maritalStatusName == null || maritalStatusName.isBlank()) {
            maritalStatusName = "Indefinido";
        }

        MaritalStatus existingOccupation = maritalStatusRepository.findByName(maritalStatusName);

        if (existingOccupation != null) {
            return existingOccupation.getId();
        }

        MaritalStatus newMaritalStatus = new MaritalStatus(maritalStatusName);
        newMaritalStatus = maritalStatusRepository.save(newMaritalStatus);
        return newMaritalStatus.getId();
    }
}
