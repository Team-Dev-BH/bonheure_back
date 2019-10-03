package com.bonheure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Societe;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
	
	Societe findByReference(String reference);

}
