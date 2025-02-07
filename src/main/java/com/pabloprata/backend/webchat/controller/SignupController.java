package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.PatientCreatedDTO;
import com.pabloprata.backend.webchat.dto.PatientSignUpDTO;
import com.pabloprata.backend.webchat.dto.PsychologistCreatedDTO;
import com.pabloprata.backend.webchat.dto.PsychologistSignUpDTO;
import com.pabloprata.backend.webchat.service.PatientService;
import com.pabloprata.backend.webchat.service.PsychologistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/psychologist")
    public ResponseEntity<PsychologistCreatedDTO> createPsychologist(@Valid @RequestBody PsychologistSignUpDTO dto) {

        PsychologistCreatedDTO psychologistCreatedDTO = psychologistService.signup(dto);



        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/psychologist/{id}")
                .buildAndExpand(psychologistCreatedDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(psychologistCreatedDTO );
    }

    @PostMapping("/patient")
    public ResponseEntity<PatientCreatedDTO> createPatients(@Valid @RequestBody PatientSignUpDTO dto) {

        PatientCreatedDTO patientCreatedDTO = patientService.signup(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/patient/{id}")
                .buildAndExpand(patientCreatedDTO.id())
                .toUri();

        return ResponseEntity.created(location).body(patientCreatedDTO);
    }
}
