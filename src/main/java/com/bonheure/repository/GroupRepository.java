package com.bonheure.repository;

import com.bonheure.domain.Group;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

	Optional<Group> findOneByReference(String reference);
	
	Set<Group> findByCompanyReference(String companyReference);

}
