package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.domain.MedicalHistory;
import com.pabloprata.backend.webchat.dto.MedicalHistoryResponseDTO;
import com.pabloprata.backend.webchat.dto.ParentalInfoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryFactory {


    public MedicalHistoryResponseDTO convertEntityToDto(MedicalHistory medicalHistoryEntity) {

        ParentalInfoResponseDTO fatherInfo = new ParentalInfoResponseDTO(
                medicalHistoryEntity.getFathersName(),
                medicalHistoryEntity.getFathersAge(),
                medicalHistoryEntity.getFathersEducation().getName(),
                medicalHistoryEntity.getFathersOccupation().getName(),
                medicalHistoryEntity.getFatherNotes());

        ParentalInfoResponseDTO mothersInfo = new ParentalInfoResponseDTO(
                medicalHistoryEntity.getMothersName(),
                medicalHistoryEntity.getMothersAge(),
                medicalHistoryEntity.getMothersEducation().getName(),
                medicalHistoryEntity.getMothersEducation().getName(),
                medicalHistoryEntity.getMotherNotes());


        return new MedicalHistoryResponseDTO(
                medicalHistoryEntity.getPatientOccupation().getName(),
                medicalHistoryEntity.getPatientMaritalStatus().getName(),
                medicalHistoryEntity.getPatientReligion().getName(),
                medicalHistoryEntity.getPatientEducation().getName(),
                fatherInfo,
                mothersInfo
        );
    }
}
