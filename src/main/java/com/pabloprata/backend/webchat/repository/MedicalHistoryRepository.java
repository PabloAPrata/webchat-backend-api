package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {


    @Query("""
                SELECT mh FROM MedicalHistory mh
                JOIN mh.patient p
                JOIN p.user u
                WHERE u.id = :userId
            """)
    Optional<MedicalHistory> findByUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("""
                INSERT INTO MedicalHistory (
                    patientId, patientOccupation, patientMaritalStatus, patientReligion, patientEducation,
                    fathersName, fathersAge, fathersEducation, fathersOccupation,
                    mothersName, mothersAge, mothersEducation, mothersOccupation,
                    fatherNotes, motherNotes
                )
                SELECT :patientId, o, m, r, e,
                       :fathersName, :fathersAge, fe, fo,
                       :mothersName, :mothersAge, me, mo,
                       :fatherNotes, :motherNotes
                FROM Occupation o, MaritalStatus m, Religion r, EducationLevel e,
                     EducationLevel fe, Occupation fo, EducationLevel me, Occupation mo
                WHERE o.id = :patientOccupationId
                  AND m.id = :patientMaritalId
                  AND r.id = :patientReligionId
                  AND e.id = :patientEducationId
                  AND fe.id = COALESCE(:fathersEducationId, fe.id)
                  AND fo.id = COALESCE(:fathersOccupationId, fo.id)
                  AND me.id = COALESCE(:mothersEducationId, me.id)
                  AND mo.id = COALESCE(:mothersOccupationId, mo.id)
            """)
    void insertMedicalHistory(@Param("patientId") Long patientId, @Param("patientOccupationId") Integer patientOccupationId, @Param("patientMaritalId") Integer patientMaritalId, @Param("patientReligionId") Integer patientReligionId, @Param("patientEducationId") Integer patientEducationId, @Param("fathersName") String fathersName, @Param("fathersAge") Integer fathersAge, @Param("fathersEducationId") Integer fathersEducationId, @Param("fathersOccupationId") Integer fathersOccupationId, @Param("mothersName") String mothersName, @Param("mothersAge") Integer mothersAge, @Param("mothersEducationId") Integer mothersEducationId, @Param("mothersOccupationId") Integer mothersOccupationId, @Param("fatherNotes") String fatherNotes, @Param("motherNotes") String motherNotes);

}
