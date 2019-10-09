package com.bonheure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Prestataire;

public interface PrestataireRepository extends JpaRepository<Prestataire, Long> {

	Optional<Prestataire> findOneByReference(String reference);

}
