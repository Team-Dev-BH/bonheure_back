package com.bonheure.service;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.domain.Category;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.User;
import com.bonheure.repository.CategoryRepository;
import com.bonheure.repository.PrestationRepository;
import com.bonheure.utils.ApiMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRespository;

	@Autowired
	PrestationRepository prestationRepository;
	@Autowired
	private ApiMapper apiMapper;

	// saveCategory

	public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

		categoryDTO.setReference(UUID.randomUUID().toString());
		Category category = apiMapper.fromDTOToBean(categoryDTO);
		categoryRespository.save(category);

		return categoryDTO;

	}

	// getCategoryByReference

	public CategoryDTO getCategoryByReference(String reference) {
		Category category = categoryRespository.findOneByReference(reference).orElse(null);

		if (category == null)
			return null;
		CategoryDTO categoryDTO = apiMapper.fromBeanToDTO(category);

		return categoryDTO;
	}

	// deleteCategoryByReference

	public void deleteCategoryByReference(String reference) {
		Category category = categoryRespository.findOneByReference(reference).orElse(null);

		categoryRespository.delete(category);

	}

	// updateCategoryByReference

	/*
	 * public CategoryDTO updateCategoryByReference(String reference, CategoryDTO
	 * categoryDTO) {
	 * 
	 * //TODO throw exception if not found Category oldCategory=
	 * categoryRespository.findOneByReference(reference).orElse(null);
	 * 
	 * if (oldCategory != null) { apiMapper.updateBeanFromDto(categoryDTO,
	 * oldCategory); categoryRespository.save(oldCategory); } return categoryDTO; }
	 */

	// getAllCategoryByReference

	public List<CategoryDTO> getAllCategory() {

		List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();

		List<Category> categorys = categoryRespository.findAll();

		for (Category category : categorys) {

			CategoryDTO categoryDTO = apiMapper.fromBeanToDTO(category);

			categoryDTOs.add(categoryDTO);

		}

		return categoryDTOs;

	}

	// //getPrestationFromCategory
/*
	public List<PrestationDTO> getListPrestationByCategory(CategoryDTO categoryDTO) {

		if (categoryDTO == null)
			return null;
		List<PrestationDTO> prestationDTOs = categoryDTO.getPrestations().stream().map(result -> {
			Prestation obj = prestationRepository.findOneByReference(result).orElse(null);
			return apiMapper.fromBeanToDTO(obj);
		}).collect(Collectors.toList());
		 
		return prestationDTOs;
	}
*/
	
	
}
