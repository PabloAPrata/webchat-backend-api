package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.dto.PatientDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByPsychologist_User_Id(UUID psychologistUserId, Pageable pagination);

    Optional<Patient> findByUser_Id(UUID userId);


    @Query("""
                SELECT u FROM User u
                WHERE (:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%')))
                  AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')))
                  AND (:phone IS NULL OR u.telephone LIKE CONCAT('%', :phone, '%'))
                  AND (:cpf IS NULL OR u.cpf = :cpf)
            """)
    List<User> searchUsers(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("cpf") String cpf
    );

    @Query("""
    SELECT new com.pabloprata.backend.webchat.dto.PatientDetailsDTO(
        u.name, u.email, u.telephone, g.name, p.patientStatus,
        a.street, a.number, a.complement, a.district, a.zipCode,
        c.name, s.name, co.name
    )
    FROM User u
    LEFT JOIN Patient p ON p.user = u
    JOIN Gender g ON g.id = u.gender.id
    LEFT JOIN Address a ON a.id = (SELECT MAX(a2.id) FROM Address a2 WHERE a2.user = u)
    LEFT JOIN City c ON c.id = a.city.id
    LEFT JOIN State s ON s.id = c.state.id
    LEFT JOIN Country co ON co.id = s.country.id
    WHERE u.id = :userId
""")
    Optional<PatientDetailsDTO> findPatientDetailsByUserId(@Param("userId") UUID userId);
}



