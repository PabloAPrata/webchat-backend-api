package com.pabloprata.backend.webchat.service;

import com.pabloprata.backend.webchat.domain.Address;
import com.pabloprata.backend.webchat.domain.City;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.dto.AddressDTO;
import com.pabloprata.backend.webchat.dto.AddressResponseDTO;
import com.pabloprata.backend.webchat.dto.UpdateUserDTO;
import com.pabloprata.backend.webchat.factory.AddressFactory;
import com.pabloprata.backend.webchat.repository.AddressRepository;
import com.pabloprata.backend.webchat.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final AddressFactory factory;

    @Transactional
    public void updateUser(UpdateUserDTO dto) {
        int updatedRows =  userRepository.updateUser(
                dto.id(),
                dto.name(),
                dto.image(),
                LocalDate.parse(dto.dateBirth()),
                dto.phoneNumber(),
                dto.email()
                );

        if (updatedRows == 0) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + dto.id());
        }
    }

    @Transactional
    public AddressResponseDTO saveUserAddress(AddressDTO dto, UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        City city = cityService.getOrCreateCity(dto.city(), dto.state(), dto.country());

        Address address = new Address();
        address.setUser(user);
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setComplement(dto.complement());
        address.setDistrict(dto.district());
        address.setZipCode(dto.zip_code());
        address.setCity(city);

        Address newAddress = addressRepository.save(address);

        return factory.convertEntityToDto(newAddress);
    }

}
