package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.DTOs.PatientResponseDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

//@RestController
//@RequestMapping("/psychologist")
//public class PsychologistController {
//
//    @Autowired
//    private PsychologistService service;
//
//    @GetMapping("/{psychologistUserId}/patients")
//    public ResponseEntity<List<PatientResponseDTO>> getPatientsByPsychologist(@PathVariable UUID psychologistUserId) {
//        List<PatientResponseDTO> patients = service.getPatientsByPsychologist(psychologistUserId);
//        return ResponseEntity.ok(patients);
//    }
//}
