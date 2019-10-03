package com.bonheure.repository;

import com.bonheure.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByReference(String reference);

}
