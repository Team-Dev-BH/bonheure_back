package com.bonheure.service;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.domain.Category;
import com.bonheure.domain.WorkingArea;
import com.bonheure.repository.CategoryRepository;
import com.bonheure.utils.ApiMapper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRespository;

    @Autowired
    private ApiMapper apiMapper;
    
    
    
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

    	categoryDTO.setReference(UUID.randomUUID().toString());
    	Category category = apiMapper.fromDTOToBean(categoryDTO);
        categoryRespository.save(category);

        return categoryDTO;

    }
    
    
    public CategoryDTO getCategoryByReference(String reference) {
    	Category category = categoryRespository.findOneByReference(reference).
                orElse(null);

        if (category == null)
            return null;
        CategoryDTO categoryDTO = apiMapper.fromBeanToDTO(category);

        return categoryDTO;
    }
    
    
    public void deleteCategoryByReference(String reference) {
    	Category category = categoryRespository.findOneByReference(reference).
                orElse(null);


       categoryRespository.delete(category);

    }
    
    public CategoryDTO updateCategoryByReference(String reference, CategoryDTO categoryDTO) {

        //TODO throw exception if not found
    	Category oldCategory= categoryRespository.findOneByReference(reference).orElse(null);

        if (oldCategory != null) {
            apiMapper.updateBeanFromDto(categoryDTO, oldCategory);
            categoryRespository.save(oldCategory);
        }
        return categoryDTO;
    }
}
