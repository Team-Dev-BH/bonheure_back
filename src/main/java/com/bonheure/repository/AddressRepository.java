package com.bonheure.repository;

import com.bonheure.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByReference(String reference);

}
