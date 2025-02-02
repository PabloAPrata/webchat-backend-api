package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.dto.PsychologistDetailsDTO;
import com.pabloprata.backend.webchat.domain.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Optional<Psychologist> findByUserId(UUID userId);

    @Query("""
                SELECT new com.pabloprata.backend.webchat.dto.PsychologistDetailsDTO(
                    u.name, u.email, u.telephone, p.crp, g.name,
                    a.street, a.number, a.complement, a.district, a.zipCode,
                    c.name, s.name, co.name
                )
                FROM Psychologist p
                JOIN p.user u
                LEFT JOIN u.gender g
                LEFT JOIN Address a ON a.user.id = u.id
                LEFT JOIN City c ON a.city.id = c.id
                LEFT JOIN State s ON c.state.id = s.id
                LEFT JOIN Country co ON s.country.id = co.id
                WHERE u.id = :userId
            """)
    Optional<PsychologistDetailsDTO> findPsychologistDetailsById(@Param("userId") UUID userId);

}
