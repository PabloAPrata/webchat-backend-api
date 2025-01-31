package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.DTOs.PatientCreatedDTO;
import com.pabloprata.backend.webchat.DTOs.PatientSignUpDTO;
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

        User savedUser = userRepository.save(patient.getUser());

        Psychologist psychologist = psychologistRepository.findByUserUserId(dto.PsychologistId())
                .orElseThrow(() -> new EntityNotFoundException("Psicólogo não encontrado para o usuário com ID: " + savedUser.getUserId()));

        patient.setPsychologist(psychologist);

        patient.setUser(savedUser);

        patientRepository.save(patient);

        return factory.convertEntityToCreatedDto(patient);
    }


}
