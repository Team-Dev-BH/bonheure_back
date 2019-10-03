package com.bonheure.service;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.domain.Category;
import com.bonheure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRespository;


    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = getCategoryFromDto(categoryDTO);

        categoryRespository.save(category);

        return categoryDTO;

    }

    public CategoryDTO getCategoryByReference(String reference) {
        Category category = categoryRespository.findByReference(reference);

        CategoryDTO categoryDTO = getCategoryDTOFromCategory(category);

        return categoryDTO;
    }


    private CategoryDTO getCategoryDTOFromCategory(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setName(category.getName());
        categoryDTO.setReference(category.getReference());

        return categoryDTO;
    }

    private Category getCategoryFromDto(CategoryDTO categoryDTO) {

        Category category = new Category();

        category.setName(categoryDTO.getName());
        category.setReference(categoryDTO.getReference());

        return category;
    }

}
