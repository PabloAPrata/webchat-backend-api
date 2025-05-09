package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
import com.pabloprata.backend.webchat.dto.PatientDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    // Retorna os detalhes do paciente baseado no ID do usuário
    @Query("""
                SELECT new com.pabloprata.backend.webchat.dto.PatientDetailsDTO(
                    u.name, u.email, u.telephone, g.name, p.patientStatus,
                    a.street, a.number, a.complement, a.district, a.zipCode,
                    c.name, s.name, co.name
                )
                FROM Patient p
                JOIN p.user u
                JOIN u.gender g
                LEFT JOIN Address a ON a.user.id = u.id
                LEFT JOIN City c ON a.city.id = c.id
                LEFT JOIN State s ON c.state.id = s.id
                LEFT JOIN Country co ON s.country.id = co.id
                WHERE u.id = :userId
            """)
    Optional<PatientDetailsDTO> findPatientDetailsByUserId(@Param("userId") UUID userId);
}



