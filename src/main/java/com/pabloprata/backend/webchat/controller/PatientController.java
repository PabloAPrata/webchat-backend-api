package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.MedicalHistoryDTO;
import com.pabloprata.backend.webchat.dto.MedicalHistoryResponseDTO;
import com.pabloprata.backend.webchat.dto.PatientDetailsDTO;
import com.pabloprata.backend.webchat.dto.PatientStatusUpdateDTO;
import com.pabloprata.backend.webchat.service.MedicalHistoryService;
import com.pabloprata.backend.webchat.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

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

    @PostMapping("/{userId}/medical-history")
    public ResponseEntity<MedicalHistoryResponseDTO> createMedicalHistory(@PathVariable UUID userId, @RequestBody MedicalHistoryDTO anamneseDTO) {

        MedicalHistoryResponseDTO medicalHistoryResponseDTO = medicalHistoryService.createMedicalHistory(userId, anamneseDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/patient/{id}/medical-history")
                .buildAndExpand(medicalHistoryResponseDTO.userId())
                .toUri();

        return ResponseEntity.created(location).body(medicalHistoryResponseDTO);
    }

    @GetMapping("/{userId}/medical-history")
    public ResponseEntity<MedicalHistoryResponseDTO> getMedicalHistory(@PathVariable UUID userId) {
        MedicalHistoryResponseDTO medicalHistory = medicalHistoryService.getMedicalHistoryByUserId(userId);
        return ResponseEntity.ok(medicalHistory);
    }

}

