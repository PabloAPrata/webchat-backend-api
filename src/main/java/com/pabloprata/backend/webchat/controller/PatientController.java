package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.DTOs.PatientStatusUpdateDTO;
import com.pabloprata.backend.webchat.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updatePatientStatus(@PathVariable UUID userId, @RequestBody @Valid PatientStatusUpdateDTO statusUpdateDTO) {
        patientService.updatePatientStatus(userId, statusUpdateDTO.patientStatus());
        return ResponseEntity.noContent().build();
    }
}

