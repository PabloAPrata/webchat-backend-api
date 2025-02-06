package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.*;
import com.pabloprata.backend.webchat.dto.MedicalHistoryDTO;
import com.pabloprata.backend.webchat.dto.MedicalHistoryResponseDTO;
import com.pabloprata.backend.webchat.factory.MedicalHistoryFactory;
import com.pabloprata.backend.webchat.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryFactory factory;
    private final PatientRepository patientRepository;

    public void createMedicalHistory(UUID userId, @Valid MedicalHistoryDTO anamneseDTO) {

        Patient patient = patientRepository.findByUser_Id(userId).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        medicalHistoryRepository.insertMedicalHistory(
                patient.getId(),
                anamneseDTO.patientOccupation(),
                anamneseDTO.patientMarital(),
                anamneseDTO.patientReligion(),
                anamneseDTO.patientEducation(),
                anamneseDTO.father().parentName(),
                anamneseDTO.father().parentAge(),
                anamneseDTO.father().parentEducation(),
                anamneseDTO.father().parentOccupation(),
                anamneseDTO.mother().parentName(),
                anamneseDTO.mother().parentAge(),
                anamneseDTO.mother().parentEducation(),
                anamneseDTO.mother().parentOccupation(),
                anamneseDTO.father().parentNotes(),
                anamneseDTO.mother().parentNotes()
        );

    }


    public MedicalHistoryResponseDTO getMedicalHistoryByUserId (UUID userId){

        MedicalHistory medicalHistory = medicalHistoryRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Histórico médico não encontrado"));

        return factory.convertEntityToDto(medicalHistory);
    }
}
