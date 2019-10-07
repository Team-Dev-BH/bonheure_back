package com.bonheure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.WorkingArea;

public interface WorkingAreaRepository extends JpaRepository<WorkingArea, Long> {
	
	Optional<WorkingArea> findOneByReference(String reference);

}
