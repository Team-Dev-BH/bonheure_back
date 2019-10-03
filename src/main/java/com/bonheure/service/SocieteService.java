package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.RoleDTO;
import com.bonheure.controller.dto.SocieteDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Role;
import com.bonheure.domain.Societe;
import com.bonheure.domain.User;
import com.bonheure.repository.SocieteRepository;

@Service
public class SocieteService {
	
	@Autowired
	SocieteRepository societeRepository;
	
	
	

    public SocieteDTO saveSociete(SocieteDTO societeDTO)
    {
       Societe societe = getSocieteFromDto(societeDTO);

        societeRepository.save(societe);

        return societeDTO;

    }

    
    public SocieteDTO getSocieteByReference(String reference)
    {
    	Societe societe =  societeRepository.findByReference(reference);

    	SocieteDTO societeDTO =  getSocieteDTOFromSociete(societe);

        return societeDTO;
    }
    
    public void  deleteSocieteByReference(String reference)
    {
       Societe societe = societeRepository.findByReference(reference);
       societeRepository.delete(societe);
    
    }
    
    public  SocieteDTO updateSocieteByReference(String reference,SocieteDTO societeDTO)
    {
    	 Societe societeOld = societeRepository.findByReference(reference);
    	 Societe societeNew = getSocieteFromDto(societeDTO);
    	 
    	if ( societeOld != null )
    	{
    		Update(societeOld,societeNew);
    	}
        return societeDTO;
    }

    
    
    
    private SocieteDTO getSocieteDTOFromSociete( Societe societe) {

    	SocieteDTO societeDTO= new SocieteDTO();

    	societeDTO.setActivityField(societe.getActivityField());
    	societeDTO.setCode(societe.getCode());
    	societeDTO.setName(societe.getName());
    	societeDTO.setReference(societe.getReference());
       
       
        return societeDTO;   
        }
    
    
    private Societe getSocieteFromDto(SocieteDTO societeDTO) {
    	Societe societe = new Societe();

         societe.setActivityField(societeDTO.getActivityField());
         societe.setCode(societeDTO.getCode());
         societe.setName(societeDTO.getName());
         societe.setReference(societeDTO.getReference());
        return societe;
    }
    
    private void Update(Societe SocieteOld ,Societe SocieteNew) {     
    	
    	SocieteOld.setActivityField(SocieteNew.getActivityField());  
    	SocieteOld.setCode(SocieteNew.getCode());
    	SocieteOld.setName(SocieteNew.getName());
    	SocieteOld.setReference(SocieteNew.getReference());
    	
   	
   	    societeRepository.save(SocieteOld);
   }
    
}
