package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.AdresseDTO;
import com.bonheure.domain.Adresse;
import com.bonheure.repository.AdresseRepository;

@Service
public class AdresseService {
	
	  @Autowired
	  AdresseRepository adresseRepository;
	 
	


	    public AdresseDTO saveAdresse(AdresseDTO adresseDTO)
	    {
	        Adresse adresse = getAdresseFromDto(adresseDTO);

	        adresseRepository.save(adresse);
	        

	        return adresseDTO;

	    }

	    public AdresseDTO getAdresseByReference(String reference)
	    {
	        Adresse adresse = adresseRepository.findByReference(reference);

	        AdresseDTO adresseDTO =  getAdresseDTOFromAdresse(adresse);

	        return adresseDTO;
	    }
	    
	    
	    
	    private AdresseDTO getAdresseDTOFromAdresse(Adresse adresse) {

	        AdresseDTO adresseDTO = new AdresseDTO();

	          
	        
	          adresseDTO.setPostalCode(adresse.getPostalCode());
	          adresseDTO.setReference(adresse.getReference());
	          adresseDTO.setRegion(adresse.getRegion());
	          adresseDTO.setStreet(adresse.getStreet());
	          adresseDTO.setType(adresse.getType());
	          
	          
	        return adresseDTO;    }
	    
	    
	    private Adresse getAdresseFromDto(AdresseDTO adresseDTO) {
	        Adresse adresse = new Adresse();
	        
	         
	        adresse.setPostalCode(adresseDTO.getPostalCode());
	        adresse.setReference(adresseDTO.getReference());
	        adresse.setRegion(adresseDTO.getRegion());
	        adresse.setStreet(adresseDTO.getStreet());
	        adresse.setType(adresseDTO.getType());

	        
	          


	        return adresse;

	   
	    }

}
