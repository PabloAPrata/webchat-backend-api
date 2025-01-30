package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.DTOs.PsychologistResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.factory.PsychologistFactory;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final PasswordEncoder passwordEncoder;
    private final PsychologistFactory factory;

    public PsychologistResponseDTO signup(PsychologistSignUpDTO dto) {

        Psychologist psychologist = factory.convertDtoToEntity(dto, passwordEncoder);

        return factory.convertEntityToResponse(psychologist);
    }

}
