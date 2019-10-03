package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.domain.Societe;
import com.bonheure.repository.CompanyRepository;

@Service
public class SocieteService {
	
	@Autowired
    CompanyRepository societyRepository;
	
	
	

    public CompanyDTO saveSociete(CompanyDTO companyDTO)
    {
       Societe societe = getSocieteFromDto(companyDTO);

        societyRepository.save(societe);

        return companyDTO;

    }

    
    public CompanyDTO getSocieteByReference(String reference)
    {
    	Societe societe =  societyRepository.findByReference(reference);

    	CompanyDTO companyDTO =  getSocieteDTOFromSociete(societe);

        return companyDTO;
    }
    
    public void  deleteSocieteByReference(String reference)
    {
       Societe societe = societyRepository.findByReference(reference);
       societyRepository.delete(societe);
    
    }
    
    public CompanyDTO updateSocieteByReference(String reference, CompanyDTO companyDTO)
    {
    	 Societe societeOld = societyRepository.findByReference(reference);
    	 Societe societeNew = getSocieteFromDto(companyDTO);
    	 
    	if ( societeOld != null )
    	{
    		Update(societeOld,societeNew);
    	}
        return companyDTO;
    }

    
    
    
    private CompanyDTO getSocieteDTOFromSociete(Societe societe) {

    	CompanyDTO companyDTO = new CompanyDTO();

    	companyDTO.setActivityField(societe.getActivityField());
    	companyDTO.setCode(societe.getCode());
    	companyDTO.setName(societe.getName());
    	companyDTO.setReference(societe.getReference());
       
       
        return companyDTO;
        }
    
    
    private Societe getSocieteFromDto(CompanyDTO companyDTO) {
    	Societe societe = new Societe();

         societe.setActivityField(companyDTO.getActivityField());
         societe.setCode(companyDTO.getCode());
         societe.setName(companyDTO.getName());
         societe.setReference(companyDTO.getReference());
        return societe;
    }
    
    private void Update(Societe SocieteOld ,Societe SocieteNew) {     
    	
    	SocieteOld.setActivityField(SocieteNew.getActivityField());  
    	SocieteOld.setCode(SocieteNew.getCode());
    	SocieteOld.setName(SocieteNew.getName());
    	SocieteOld.setReference(SocieteNew.getReference());
    	
   	
   	    societyRepository.save(SocieteOld);
   }
    
}
