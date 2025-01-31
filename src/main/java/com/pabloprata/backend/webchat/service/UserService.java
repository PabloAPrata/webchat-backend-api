package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.DTOs.UpdateUserDTO;
import com.pabloprata.backend.webchat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void update(UpdateUserDTO dto) {

        int updatedRows =  repository.updateUser(
                dto.id(),
                dto.name(),
                dto.image(),
                dto.dateBirth(),
                dto.phoneNumber(),
                dto.email()
                );

        if (updatedRows == 0) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + dto.id());
        }
    }
}
