package com.bonheure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Adresse;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {
	
	Adresse findByReference(String reference);

}
