package com.bonheure.repository;

import com.bonheure.domain.Category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category>  findOneByReference(String reference);
    
    Optional<Category>  findOneByName(String name);

  
    
   List<Category> findAll();  
  
}
	 
