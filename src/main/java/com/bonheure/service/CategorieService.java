package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.CategorieDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Categorie;
import com.bonheure.domain.User;
import com.bonheure.repository.CategorieRepository;


     @Service
     public class CategorieService {
     @Autowired
	
	 CategorieRepository categorieRespository;
	
	
     public CategorieDTO saveCategorie(CategorieDTO categorieDTO)
{
    Categorie categorie = getCategFromDto(categorieDTO);

    categorieRespository.save(categorie);

    return categorieDTO;

}

     public CategorieDTO getCategByReference(String reference)
{
    Categorie categorie = categorieRespository.findByReference(reference);

    CategorieDTO categorieDTO =  getCategDTOFromCateg(categorie);

    return categorieDTO;
}

     
     
     
     
     
     
     
     private CategorieDTO  getCategDTOFromCateg(Categorie categorie) {
	
	CategorieDTO categorieDTO=new CategorieDTO();
	
	categorieDTO.setName(categorie.getName());
	categorieDTO.setReference(categorie.getReference());
	
	return categorieDTO;
}

      private Categorie getCategFromDto(CategorieDTO categorieDTO) {
    	  
    	  Categorie categorie=new Categorie();
    	  
    		categorie.setName(categorieDTO.getName());	  
    		categorie.setReference(categorieDTO.getReference());
    			
    		return categorie;
      }

}
