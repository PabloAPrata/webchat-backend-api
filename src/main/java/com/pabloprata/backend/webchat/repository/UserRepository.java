package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.profileImg = :profileImg, u.dateBirth = :dateBirth, " +
            "u.telephone = :phoneNumber, u.email = :email WHERE u.id = :id")
    int updateUser(UUID id, String name, String profileImg, Date dateBirth, String phoneNumber, String email);
}
