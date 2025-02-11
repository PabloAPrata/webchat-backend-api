package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.domain.Address;
import com.pabloprata.backend.webchat.dto.AddressResponseDTO;
import com.pabloprata.backend.webchat.dto.StateDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {

    public AddressResponseDTO convertEntityToDto(Address address) {

        StateDTO stateDTO = new StateDTO(address.getCity().getState().getName(),
                address.getCity().getState().getUf());

        return new AddressResponseDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getDistrict(),
                address.getZipCode(),
                address.getCity().getName(),
                stateDTO,
                address.getCity().getState().getCountry().getName()
        );
    }
}
