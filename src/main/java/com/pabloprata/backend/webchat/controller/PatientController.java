package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.PatientDetailsDTO;
import com.pabloprata.backend.webchat.dto.PatientStatusUpdateDTO;
import com.pabloprata.backend.webchat.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/{userId}")
    public ResponseEntity<PatientDetailsDTO> getPatientDetails(@PathVariable UUID userId) {
        PatientDetailsDTO details = service.getPatientDetails(userId);
        return ResponseEntity.ok(details);
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updatePatientStatus(@PathVariable UUID userId, @RequestBody @Valid PatientStatusUpdateDTO statusUpdateDTO) {
        service.updatePatientStatus(userId, statusUpdateDTO.patientStatus());
        return ResponseEntity.noContent().build();
    }
}

