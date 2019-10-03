package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.repository.CategoryRepository;


     @Service
     public class CategorieService {
     @Autowired

     CategoryRepository categorieRespository;
	
	
     public CategoryDTO saveCategorie(CategoryDTO categoryDTO)
{
    Categorie categorie = getCategFromDto(categoryDTO);

    categorieRespository.save(categorie);

    return categoryDTO;

}

     public CategoryDTO getCategByReference(String reference)
{
    Categorie categorie = categorieRespository.findByReference(reference);

    CategoryDTO categoryDTO =  getCategDTOFromCateg(categorie);

    return categoryDTO;
}

     
     
     
     
     
     
     
     private CategoryDTO getCategDTOFromCateg(Categorie categorie) {
	
	CategoryDTO categoryDTO =new CategoryDTO();
	
	categoryDTO.setName(categorie.getName());
	categoryDTO.setReference(categorie.getReference());
	
	return categoryDTO;
}

      private Categorie getCategFromDto(CategoryDTO categoryDTO) {
    	  
    	  Categorie categorie=new Categorie();
    	  
    		categorie.setName(categoryDTO.getName());
    		categorie.setReference(categoryDTO.getReference());
    			
    		return categorie;
      }

}
