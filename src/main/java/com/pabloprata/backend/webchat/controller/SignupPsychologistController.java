package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.DTOs.PsychologistResponseDTO;
import com.pabloprata.backend.webchat.DTOs.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupPsychologistController {

    @Autowired
    private PsychologistService service;

    @PostMapping("/psychologist")
    public PsychologistResponseDTO createPsychologist(@Valid @RequestBody PsychologistSignUpDTO dto) {
        return service.signup(dto);
    }
}
