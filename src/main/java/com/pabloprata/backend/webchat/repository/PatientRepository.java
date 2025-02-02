package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Patient;
import com.pabloprata.backend.webchat.domain.User;
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
    Page<Patient> findByPsychologist_User_UserId(UUID psychologistUserId, Pageable pagination);

    Optional<Patient> findByUser_UserId(UUID userId);

    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:phone IS NULL OR u.telephone LIKE CONCAT('%', :phone, '%')) " +
            "AND (:cpf IS NULL OR u.cpf = :cpf)")
    List<User> searchUsers(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("cpf") String cpf);

}
