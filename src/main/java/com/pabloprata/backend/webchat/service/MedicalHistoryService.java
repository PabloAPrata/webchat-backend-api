package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.*;
import com.pabloprata.backend.webchat.dto.MedicalHistoryDTO;
import com.pabloprata.backend.webchat.dto.MedicalHistoryResponseDTO;
import com.pabloprata.backend.webchat.factory.MedicalHistoryFactory;
import com.pabloprata.backend.webchat.repository.*;
import com.pabloprata.backend.webchat.infra.error.exceptions.AlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryFactory factory;
    private final EducationService educationService;
    private final MaritalStatusService maritalStatusService;
    private final OccupationService occupationService;
    private final ReligionService religionService;
    private final PatientRepository patientRepository;

    @Transactional
    public MedicalHistoryResponseDTO createMedicalHistory(UUID userId, @Valid MedicalHistoryDTO anamneseDTO) {

        Patient patient = patientRepository.findByUser_Id(userId).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        if(medicalHistoryRepository.existsById(patient.getId())) {
            throw new AlreadyExistsException("O paciente já possui um histórico médico!");
        }

        medicalHistoryRepository.insertMedicalHistory(
                patient.getId(),
                occupationService.getOrCreateOccupation(anamneseDTO.patientOccupation()),
                maritalStatusService.getOrCreateMaritalStatus(anamneseDTO.patientMarital()),
                religionService.getOrCreateReligion(anamneseDTO.patientReligion()),
                educationService.getOrCreateEducationLevel(anamneseDTO.patientEducation()),
                anamneseDTO.father().parentName(),
                anamneseDTO.father().parentAge(),
                educationService.getOrCreateEducationLevel(anamneseDTO.father().parentEducation()),
                occupationService.getOrCreateOccupation(anamneseDTO.father().parentOccupation()),
                anamneseDTO.mother().parentName(),
                anamneseDTO.mother().parentAge(),
                educationService.getOrCreateEducationLevel(anamneseDTO.mother().parentEducation()),
                occupationService.getOrCreateOccupation(anamneseDTO.mother().parentOccupation()),
                anamneseDTO.father().parentNotes(),
                anamneseDTO.mother().parentNotes()
        );

        return new MedicalHistoryResponseDTO(
                userId,
                anamneseDTO.patientOccupation(),
                anamneseDTO.patientMarital(),
                anamneseDTO.patientReligion(),
                anamneseDTO.patientEducation(),
                anamneseDTO.father(),
                anamneseDTO.mother()
        );
    }

    @Transactional(readOnly = true)
    public MedicalHistoryResponseDTO getMedicalHistoryByUserId (UUID userId){

        MedicalHistory medicalHistory = medicalHistoryRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Histórico médico não encontrado"));

        return factory.convertEntityToDto(medicalHistory);
    }





}
