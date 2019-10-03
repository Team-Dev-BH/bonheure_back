package com.bonheure.repository;

import com.bonheure.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByReference(String reference);

}
