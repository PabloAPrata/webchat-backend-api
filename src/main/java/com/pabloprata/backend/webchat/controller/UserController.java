package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.DTOs.UpdateUserDTO;
import com.pabloprata.backend.webchat.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserDTO dto) {
        userService.update(dto);
        return ResponseEntity.noContent().build();
    }
}
