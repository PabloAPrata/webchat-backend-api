package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.DTOs.PsychologistResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class PsychologistFactory {

    public Psychologist convertDtoToEntity(PsychologistSignUpDTO dto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setName(dto.firstName() + " " + dto.lastName());
        user.setCpf(dto.cpf());
        user.setTelephone(dto.phoneNumber());
        user.setEmail(dto.email());
        user.setGender(User.Gender.valueOf(dto.gender()));
        user.setPassword(passwordEncoder.encode(dto.password()));

        Psychologist psychologist = new Psychologist();
        psychologist.setUser(user);
        psychologist.setCrp(dto.crp());

        return psychologist;
    }

    public PsychologistResponseDTO convertEntityToResponse (Psychologist psychologist) {

        String fullName = psychologist.getUser().getName();
        String[] nameParts = fullName.split(" ", 3);

        String firstName = nameParts.length > 0 ? nameParts[0] : "";
        String middleName =  nameParts.length > 1 ? nameParts[1] : "";
        String lastName = nameParts.length > 1 ? nameParts[2] : "";

        return new PsychologistResponseDTO(
                psychologist.getUser().getUserId(),
                firstName,
                middleName,
                lastName,
                psychologist.getCrp(),
                psychologist.getUser().getTelephone(),
                psychologist.getUser().getEmail(),
                psychologist.getUser().getCreated_at()
        );
    }
}
