package com.bonheure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bonheure.domain.Categorie;
import com.bonheure.domain.User;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Categorie findByReference(String reference);

}
