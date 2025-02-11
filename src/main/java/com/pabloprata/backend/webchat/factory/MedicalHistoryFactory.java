package com.pabloprata.backend.webchat.factory;

import com.pabloprata.backend.webchat.domain.MedicalHistory;
import com.pabloprata.backend.webchat.dto.MedicalHistoryResponseDTO;
import com.pabloprata.backend.webchat.dto.ParentInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryFactory {


    public MedicalHistoryResponseDTO convertEntityToDto(MedicalHistory medicalHistoryEntity) {

        ParentInfoDTO fatherInfo = new ParentInfoDTO(
                medicalHistoryEntity.getFathersName(),
                medicalHistoryEntity.getFathersAge(),
                medicalHistoryEntity.getFathersEducation() != null ? medicalHistoryEntity.getFathersEducation().getName() : null,
                medicalHistoryEntity.getFathersOccupation() != null ? medicalHistoryEntity.getFathersOccupation().getName() : null,
                medicalHistoryEntity.getFatherNotes()
        );

        ParentInfoDTO mothersInfo = new ParentInfoDTO(
                medicalHistoryEntity.getMothersName(),
                medicalHistoryEntity.getMothersAge(),
                medicalHistoryEntity.getMothersEducation() != null ? medicalHistoryEntity.getMothersEducation().getName() : null,
                medicalHistoryEntity.getMothersOccupation() != null ? medicalHistoryEntity.getMothersOccupation().getName() : null,
                medicalHistoryEntity.getMotherNotes()
        );

        return new MedicalHistoryResponseDTO(
                medicalHistoryEntity.getPatient().getUser().getId(),
                medicalHistoryEntity.getPatientOccupation() != null ? medicalHistoryEntity.getPatientOccupation().getName() : null,
                medicalHistoryEntity.getPatientMaritalStatus() != null ? medicalHistoryEntity.getPatientMaritalStatus().getName() : null,
                medicalHistoryEntity.getPatientReligion() != null ? medicalHistoryEntity.getPatientReligion().getName() : null,
                medicalHistoryEntity.getPatientEducation() != null ? medicalHistoryEntity.getPatientEducation().getName() : null,
                fatherInfo,
                mothersInfo
        );
    }

}
