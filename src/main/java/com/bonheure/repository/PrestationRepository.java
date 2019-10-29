package com.bonheure.repository;

import com.bonheure.domain.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;



public interface PrestationRepository extends JpaRepository<Prestation, Long> {

    Optional<Prestation> findOneByReference(String reference);

}
