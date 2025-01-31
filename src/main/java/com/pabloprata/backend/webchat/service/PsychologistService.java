package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.DTOs.PsychologistResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.factory.PsychologistFactory;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final PsychologistFactory factory;

    @Transactional
    public PsychologistResponseDTO signup(PsychologistSignUpDTO dto) {

        Psychologist psychologist = factory.convertDtoToEntity(dto);

        User savedUser = userRepository.save(psychologist.getUser());

        psychologist.setUser(savedUser);

        psychologistRepository.save(psychologist);

        return factory.convertEntityToResponse(psychologist);
    }

}
