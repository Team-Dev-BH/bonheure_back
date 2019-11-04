package com.bonheure.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	
	Optional<Commande> findOneByReference(String reference);
	

}