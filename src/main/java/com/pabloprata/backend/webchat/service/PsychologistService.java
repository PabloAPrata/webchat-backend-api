package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.DTOs.PatientResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.factory.PatientFactory;
import com.pabloprata.backend.webchat.factory.PsychologistFactory;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.repository.PatientRepository;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PsychologistFactory psychologistFactory;
    private final PatientFactory patientFactory;

    @Transactional
    public PsychologistCreatedDTO signup(PsychologistSignUpDTO dto) {

        Psychologist psychologist = psychologistFactory.convertDtoToEntity(dto);

        User savedUser = userRepository.save(psychologist.getUser());

        psychologist.setUser(savedUser);

        psychologistRepository.save(psychologist);

        return psychologistFactory.convertEntityToResponse(psychologist);
    }

    public List<PatientResponseDTO> getPatientsByPsychologist(UUID psychologistId) {

        List<Patient> patientsEntity = patientRepository.findByPsychologist_User_UserId(psychologistId);

        return patientsEntity.stream()
                .map(patientFactory::convertEntityToResponse)
                .toList();
    }

}
