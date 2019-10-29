package com.bonheure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Quotation;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

	Optional<Quotation> findOneByReference(String reference);
}
