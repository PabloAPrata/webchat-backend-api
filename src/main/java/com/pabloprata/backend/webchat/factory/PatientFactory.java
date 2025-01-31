package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.DTOs.PatientCreatedDTO;
import com.pabloprata.backend.webchat.DTOs.UserResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PatientSignUpDTO;
import com.pabloprata.backend.webchat.domain.Gender;
import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.repository.GenderRepository;
import com.pabloprata.backend.webchat.repository.PsychologistRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        User user = new User();
        user.setName(dto.firstName() + " " + " " + dto.lastName());
        user.setCpf(dto.cpf());
        user.setTelephone(dto.phoneNumber());
        user.setEmail(dto.email());
        user.setGender(gender);

        Patient patient = new Patient();
        patient.setUser(user);

        return patient;
    }

    public PatientCreatedDTO convertEntityToCreatedDto(Patient patient) {

        String fullName = patient.getUser().getName();
        String[] nameParts = fullName.split(" ", 3);

        int nameLength = nameParts.length;

        String firstName = nameLength > 0 ? nameParts[0] : "";
        String lastName = nameLength > 1 ? nameParts[nameLength - 1] : "";

        return new PatientCreatedDTO(
                patient.getUser().getUserId(),
                firstName,
                lastName,
                patient.getUser().getTelephone(),
                patient.getUser().getEmail(),
                patient.getUser().getCreatedAt()
        );
    }

    public UserResponseDTO convertEntityToResponse(Patient patient) {

        String dateBirth = (patient.getUser().getDateBirth() != null) ? patient.getUser().getDateBirth().toString() : null;

        return new UserResponseDTO(
                patient.getUser().getUserId(),
                patient.getUser().getName(),
                patient.getUser().getCpf(),
                patient.getUser().getTelephone(),
                patient.getUser().getEmail(),
                patient.getUser().getProfileImg(),
                dateBirth
        );
    }
}
