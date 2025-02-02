package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.PsychologistDetailsDTO;
import com.pabloprata.backend.webchat.dto.UserResponseDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/psychologist")
public class PsychologistController {

    @Autowired
    private PsychologistService service;

    @GetMapping("/{userId}")
    public ResponseEntity<PsychologistDetailsDTO> getPsychologistDetails(@PathVariable UUID userId) {
        PsychologistDetailsDTO details = service.getPsychologistDetails(userId);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{psychologistUserId}/patients")
    public ResponseEntity<Page<UserResponseDTO>> getPatientsByPsychologist(@PathVariable UUID psychologistUserId, @PageableDefault(sort = "user.name") Pageable pagination) {
        Page<UserResponseDTO> patientsPage = service.getPatientsByPsychologist(psychologistUserId, pagination);
        return ResponseEntity.ok(patientsPage);
    }

}
