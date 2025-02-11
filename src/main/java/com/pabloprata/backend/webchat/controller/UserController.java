package com.pabloprata.backend.webchat.controller;

import com.pabloprata.backend.webchat.dto.AddressDTO;
import com.pabloprata.backend.webchat.dto.AddressResponseDTO;
import com.pabloprata.backend.webchat.dto.UpdateUserDTO;
import com.pabloprata.backend.webchat.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UpdateUserDTO dto) {
        userService.updateUser(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/address")
    public ResponseEntity<AddressResponseDTO> createUserAddress(@PathVariable UUID userId, @Valid @RequestBody AddressDTO dto) {
        AddressResponseDTO userAddress = userService.saveUserAddress(dto, userId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{id}")
                .buildAndExpand(userAddress.id())
                .toUri();

        return ResponseEntity.created(location).body(userAddress);
    }
}
