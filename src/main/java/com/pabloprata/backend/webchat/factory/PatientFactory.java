package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.dto.PatientCreatedDTO;
import com.pabloprata.backend.webchat.dto.UserResponseDTO;
import com.pabloprata.backend.webchat.dto.PatientSignUpDTO;
import com.pabloprata.backend.webchat.domain.Gender;
import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.repository.GenderRepository;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class PatientFactory {

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PsychologistRepository psychologistRepository;

    public Patient convertSignupDtoToEntity(PatientSignUpDTO dto) {

        Gender gender = genderRepository.findById(Long.valueOf(dto.genderId()))
                .orElseThrow(() -> new IllegalArgumentException("Gender não encontrado!"));

        String dataString = dto.dateBirth();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dataString, format);

        User user = new User();
        user.setName(dto.name());
        user.setCpf(dto.cpf());
        user.setTelephone(dto.phoneNumber());
        user.setEmail(dto.email());
        user.setDateBirth(date);
        user.setGender(gender);

        Patient patient = new Patient();
        patient.setUser(user);

        return patient;
    }

    public PatientCreatedDTO convertEntityToCreatedDto(Patient patient) {
        return new PatientCreatedDTO(
                patient.getUser().getId(),
                patient.getUser().getName(),
                patient.getUser().getTelephone(),
                patient.getUser().getEmail()
        );
    }

    public UserResponseDTO convertEntityToResponse(Patient patient) {

        String dateBirth = (patient.getUser().getDateBirth() != null) ? patient.getUser().getDateBirth().toString() : null;

        return new UserResponseDTO(
                patient.getUser().getId(),
                patient.getUser().getName(),
                patient.getUser().getCpf(),
                patient.getUser().getTelephone(),
                patient.getUser().getEmail(),
                patient.getPatientStatus(),
                patient.getUser().getProfileImg(),
                dateBirth
        );
    }
}
