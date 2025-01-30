package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signup")
public class RegisterController {

    @PostMapping("/psychologist")
    public void createPsychologist(@Valid @RequestBody PsychologistSignUpDTO dto) {
        System.out.println(dto);
    }
}
