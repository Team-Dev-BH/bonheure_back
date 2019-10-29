package com.bonheure.repository;

import com.bonheure.domain.Address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findOneByReference(String reference);

}
