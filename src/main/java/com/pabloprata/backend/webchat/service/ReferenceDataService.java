package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.EducationLevel;
import com.pabloprata.backend.webchat.domain.MaritalStatus;
import com.pabloprata.backend.webchat.domain.Occupation;
import com.pabloprata.backend.webchat.domain.Religion;
import com.pabloprata.backend.webchat.dto.EducationLevelDTO;
import com.pabloprata.backend.webchat.dto.MaritalStatusDTO;
import com.pabloprata.backend.webchat.dto.OccupationDTO;
import com.pabloprata.backend.webchat.dto.ReligionDTO;
import com.pabloprata.backend.webchat.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReferenceDataService {

    private final OccupationRepository occupationRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final ReligionRepository religionRepository;
    private final EducationLevelRepository educationLevelRepository;

    @Transactional(readOnly = true)
    public Page<OccupationDTO> getAllOccupations(Pageable pagination) {
        Page<Occupation> occupationPage = occupationRepository.findAll(pagination);

        return occupationPage.map(occupation ->
                new OccupationDTO(occupation.getId(), occupation.getName())
        );
    }


    @Transactional(readOnly = true)
    public Page<MaritalStatusDTO> getAllMaritalStatuses(Pageable pagination) {
        Page<MaritalStatus> maritalStatusesPage = maritalStatusRepository.findAll(pagination);
        return maritalStatusesPage.map(maritalStatus ->
                new MaritalStatusDTO(maritalStatus.getId().longValue(), maritalStatus.getName())
        );
    }

    @Transactional(readOnly = true)
    public Page<ReligionDTO> getAllReligions(Pageable pagination) {
        Page<Religion> religionsPage = religionRepository.findAll(pagination);
        return religionsPage.map(religion ->
                new ReligionDTO(religion.getId().longValue(), religion.getName())
        );
    }

    @Transactional(readOnly = true)
    public Page<EducationLevelDTO> getAllEducationLevels(Pageable pagination) {
        Page<EducationLevel> educationLevelsPage = educationLevelRepository.findAll(pagination);
        return educationLevelsPage.map(educationLevel ->
                new EducationLevelDTO(educationLevel.getId(), educationLevel.getName())
        );
    }
}
