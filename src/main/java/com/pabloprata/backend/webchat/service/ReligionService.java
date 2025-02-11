package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.Religion;
import com.pabloprata.backend.webchat.repository.ReligionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReligionService {

    private final ReligionRepository religionRepository;

    public Integer getOrCreateReligion(String religionName) {

        Religion existingReligion = religionRepository.findByName(religionName);

        if (existingReligion != null) {
            return existingReligion.getId();
        }

        Religion newReligion = new Religion(religionName);
        newReligion = religionRepository.save(newReligion);
        return newReligion.getId();
    }
}
