package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.GroupeDTO;
import com.bonheure.controller.dto.SocieteDTO;
import com.bonheure.domain.Groupe;
import com.bonheure.domain.Societe;
import com.bonheure.repository.GroupeRepository;

@Service
public class GroupeService {
	
	@Autowired
	GroupeRepository groupeRepository;
	
	
	

    public GroupeDTO saveGroupe(GroupeDTO groupeDTO)
    {
    	Groupe groupe = getGroupeFromDto(groupeDTO);

    	groupeRepository.save(groupe);

        return groupeDTO;

    }

    
    public GroupeDTO getGroupeByReference(String reference)
    {
    	Groupe groupe  =  groupeRepository.findByReference(reference);

    	GroupeDTO groupeDTO=  getGroupeDTOFromGroupe(groupe);

        return groupeDTO;
    }
    
    public void  deleteGroupeByReference(String reference)
    {
    	Groupe groupe = groupeRepository.findByReference(reference);
    	groupeRepository.delete(groupe);
    
    }
    
    public  GroupeDTO  updateGroupeByReference(String reference,GroupeDTO groupeDTO)
    {
    	Groupe groupeOld = groupeRepository.findByReference(reference);
    	Groupe groupeNew = getGroupeFromDto(groupeDTO);
    	 
    	if ( groupeOld != null )
    	{
    		Update(groupeOld,groupeNew);
    	}
        return groupeDTO;
    }

    
    
    
    private GroupeDTO getGroupeDTOFromGroupe( Groupe groupe) {

    	GroupeDTO groupeDTO= new GroupeDTO();

    	groupeDTO.setName(groupe.getName());
    	groupeDTO.setReference(groupe.getReference());
       
       
        return groupeDTO;   
        }
    
    
    private Groupe getGroupeFromDto(GroupeDTO groupeDTO) {
    	Groupe groupe = new Groupe();

         groupe.setName(groupeDTO.getName());
         groupe.setReference(groupeDTO.getReference());
         
        return groupe;
    }
    
    private void Update(Groupe GroupeOld ,Groupe GroupeNew) {     
    	
    	GroupeOld.setName(GroupeNew.getName()); 	
    	GroupeOld.setReference(GroupeNew.getReference());
   	
    	groupeRepository.save(GroupeOld);
   }
}
