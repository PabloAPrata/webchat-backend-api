package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.domain.Gender;
import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class PsychologistFactory {

    @Autowired
    private GenderRepository genderRepository;

    public Psychologist convertDtoToEntity(PsychologistSignUpDTO dto) {

        Gender gender = genderRepository.findById(dto.genderId().longValue()).orElseThrow(() -> new IllegalArgumentException("Gender não encontrado!"));

        String dataString = dto.dateBirth();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dataString, format);

        User user = new User();
        user.setName(dto.firstName() + " " + dto.middleName() + " " + dto.lastName());
        user.setCpf(dto.cpf());
        user.setTelephone(dto.phoneNumber());
        user.setEmail(dto.email());
        user.setDateBirth(date);
        user.setGender(gender);

        Psychologist psychologist = new Psychologist();
        psychologist.setUser(user);
        psychologist.setCrp(dto.crp());
        psychologist.setPassword(dto.password());

        return psychologist;
    }


    public PsychologistCreatedDTO convertEntityToResponse(Psychologist psychologist) {

        String fullName = psychologist.getUser().getName();
        String[] nameParts = fullName.split(" ", 3);

        int nameLength = nameParts.length;

        String firstName = nameLength > 0 ? nameParts[0] : "";
        String lastName = nameLength > 1 ? nameParts[nameLength - 1] : "";

        return new PsychologistCreatedDTO(psychologist.getUser().getId(), firstName, lastName, psychologist.getCrp(), psychologist.getUser().getTelephone(), psychologist.getUser().getEmail());
    }
}
