package com.bonheure.repository;

import com.bonheure.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> {

	long deleteByReference(String reference);

	boolean existsByEmail(String email);
	boolean existsByCompany( String reference);
	
	Client findByEmail(String email);
	
    Optional<Client> findOneByReference(String reference);

}