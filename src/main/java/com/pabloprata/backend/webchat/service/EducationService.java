package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.EducationLevel;
import com.pabloprata.backend.webchat.repository.EducationLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationLevelRepository educationLevelRepository;

    public Integer getOrCreateEducationLevel(String educationLevelName) {

        if (educationLevelName == null || educationLevelName.isBlank()) {
            educationLevelName = "Indefinido";
        }

        EducationLevel existingEducationLevel = educationLevelRepository.findByName(educationLevelName);

        if (existingEducationLevel != null) {
            return existingEducationLevel.getId();
        }

        EducationLevel newEducation = new EducationLevel(educationLevelName);
        newEducation = educationLevelRepository.save(newEducation);
        return newEducation.getId();
    }
}
