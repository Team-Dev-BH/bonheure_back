package com.bonheure.repository;

import com.bonheure.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findOneByReference(String reference);

	Optional<Company> findOneByDomainName(String domainName);

	long deleteByReference(String reference);

}