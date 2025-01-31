package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.DTOs.PatientResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PatientSignUpDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.service.PatientService;
import com.pabloprata.backend.webchat.service.PsychologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/psychologist")
    public PsychologistResponseDTO createPsychologist(@Valid @RequestBody PsychologistSignUpDTO dto) {
        return psychologistService.signup(dto);
    }

    @PostMapping("/patient")
    public PatientResponseDTO createPatients(@Valid @RequestBody PatientSignUpDTO dto) {
        return patientService.signup(dto);
    }
}
