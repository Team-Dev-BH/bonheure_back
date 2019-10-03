package com.bonheure.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
	
	Groupe findByReference(String reference);

}
