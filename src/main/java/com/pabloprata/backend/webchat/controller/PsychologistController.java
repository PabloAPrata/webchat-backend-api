package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.domain.Psychologist;
import com.pabloprata.backend.webchat.config.security.DataAuthDTO;
import com.pabloprata.backend.webchat.config.security.LoginTokenDTO;
import com.pabloprata.backend.webchat.dto.PsychologistDetailsDTO;
import com.pabloprata.backend.webchat.dto.UserResponseDTO;
import com.pabloprata.backend.webchat.service.PsychologistService;
import com.pabloprata.backend.webchat.config.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/psychologist")
public class PsychologistController {

    @Autowired
    private PsychologistService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid DataAuthDTO auth) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(auth.login(), auth.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((Psychologist) authentication.getPrincipal());

        return ResponseEntity.ok(new LoginTokenDTO(tokenJWT));
    }

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
