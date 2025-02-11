package com.pabloprata.backend.webchat.repository;

import com.pabloprata.backend.webchat.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
