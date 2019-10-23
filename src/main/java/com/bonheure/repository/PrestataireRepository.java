package com.bonheure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Prestataire;
import com.bonheure.domain.User;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {

	Optional<Prestataire> findOneByReference(String reference);
	
	long deleteByReference(String reference);

	boolean existsByMobileNumber(String mobileNumber);
	
	
	Prestataire findByMobileNumber(String mobileNumber);
	
	Prestataire  findByEmail(String mobileNumber);

}
