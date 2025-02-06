package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.EducationLevelDTO;
import com.pabloprata.backend.webchat.dto.MaritalStatusDTO;
import com.pabloprata.backend.webchat.dto.OccupationDTO;
import com.pabloprata.backend.webchat.dto.ReligionDTO;
import com.pabloprata.backend.webchat.service.ReferenceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reference-data")
public class ReferenceDataController {

    @Autowired
    private ReferenceDataService service;

    @GetMapping("/occupations")
    public ResponseEntity<Page<OccupationDTO>> getOccupations(@PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.getAllOccupations(pagination));
    }

    @GetMapping("/marital-statuses")
    public ResponseEntity<Page<MaritalStatusDTO>> getMaritalStatuses(@PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.getAllMaritalStatuses(pagination));
    }

    @GetMapping("/religions")
    public ResponseEntity<Page<ReligionDTO>> getReligions(@PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.getAllReligions(pagination));
    }

    @GetMapping("/education-levels")
    public ResponseEntity<Page<EducationLevelDTO>> getEducationLevels(@PageableDefault(sort = "name") Pageable pagination) {
        return ResponseEntity.ok(service.getAllEducationLevels(pagination));
    }
}
