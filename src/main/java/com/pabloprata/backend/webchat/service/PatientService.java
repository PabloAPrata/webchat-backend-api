package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.dto.PatientCreatedDTO;
import com.pabloprata.backend.webchat.dto.PatientDetailsDTO;
import com.pabloprata.backend.webchat.dto.PatientSignUpDTO;
import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.factory.PatientFactory;
import com.pabloprata.backend.webchat.repository.PatientRepository;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final PatientFactory factory;

    @Transactional
    public PatientCreatedDTO signup(PatientSignUpDTO dto) {

        Patient patient = factory.convertSignupDtoToEntity(dto);

        patient.setPatientStatus("ativo");

        User savedUser = userRepository.save(patient.getUser());

        Psychologist psychologist = psychologistRepository.findByUserUserId(dto.PsychologistId()).orElseThrow(() -> new EntityNotFoundException("Psicólogo não encontrado para o usuário com ID: " + savedUser.getUserId()));

        patient.setPsychologist(psychologist);

        patient.setUser(savedUser);

        patientRepository.save(patient);

        return factory.convertEntityToCreatedDto(patient);
    }

    @Transactional
    public void updatePatientStatus(UUID userId, String newStatus) {

        Patient patient = patientRepository.findByUser_UserId(userId).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado para o usuário ID: " + userId));

        if (!isValidStatus(newStatus)) {
            throw new IllegalArgumentException("Status inválido: " + newStatus);
        }

        patient.setPatientStatus(newStatus);
        patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public PatientDetailsDTO getPatientDetails(UUID userId) {
        return patientRepository.findPatientDetailsByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
    }

    private boolean isValidStatus(String status) {
        return status.equals("ativo") || status.equals("acompanhamento") || status.equals("finalizado");
    }

}
