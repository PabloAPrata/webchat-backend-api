package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.dto.PsychologistDetailsDTO;
import com.pabloprata.backend.webchat.dto.UserResponseDTO;
import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.factory.PatientFactory;
import com.pabloprata.backend.webchat.factory.PsychologistFactory;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.repository.PatientRepository;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PsychologistFactory psychologistFactory;
    private final PatientFactory patientFactory;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public PsychologistCreatedDTO signup(PsychologistSignUpDTO dto) {

        Psychologist psychologist = psychologistFactory.convertDtoToEntity(dto);

        User savedUser = userRepository.save(psychologist.getUser());

        psychologist.setUser(savedUser);

        psychologist.setPassword(passwordEncoder.encode(dto.password()));

        psychologistRepository.save(psychologist);

        return psychologistFactory.convertEntityToResponse(psychologist);
    }

    @Transactional(readOnly = true)
    public PsychologistDetailsDTO getPsychologistDetails(UUID userId) {
        return psychologistRepository.findPsychologistDetailsById(userId).orElseThrow(() -> new EntityNotFoundException("Psicólogo não encontrado"));
    }

    @Transactional(readOnly = true)
    public Page<UserResponseDTO> getPatientsByPsychologist(UUID psychologistId, Pageable pagination) {

        Page<Patient> patientsPage = patientRepository.findByPsychologist_User_Id(psychologistId, pagination);

        return patientsPage.map(patientFactory::convertEntityToResponse);
    }

}
