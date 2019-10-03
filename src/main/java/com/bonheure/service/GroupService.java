package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.domain.Groupe;
import com.bonheure.domain.Societe;
import com.bonheure.repository.GroupRepository;

@Service
public class GroupeService {
	
	@Autowired
    GroupRepository groupRepository;
	
	
	

    public GroupDTO saveGroupe(GroupDTO groupDTO)
    {
    	Groupe groupe = getGroupeFromDto(groupDTO);

    	groupRepository.save(groupe);

        return groupDTO;

    }

    
    public GroupDTO getGroupeByReference(String reference)
    {
    	Groupe groupe  =  groupRepository.findByReference(reference);

    	GroupDTO groupDTO =  getGroupeDTOFromGroupe(groupe);

        return groupDTO;
    }
    
    public void  deleteGroupeByReference(String reference)
    {
    	Groupe groupe = groupRepository.findByReference(reference);
    	groupRepository.delete(groupe);
    
    }
    
    public GroupDTO updateGroupeByReference(String reference, GroupDTO groupDTO)
    {
    	Groupe groupeOld = groupRepository.findByReference(reference);
    	Groupe groupeNew = getGroupeFromDto(groupDTO);
    	 
    	if ( groupeOld != null )
    	{
    		Update(groupeOld,groupeNew);
    	}
        return groupDTO;
    }

    
    
    
    private GroupDTO getGroupeDTOFromGroupe(Groupe groupe) {

    	GroupDTO groupDTO = new GroupDTO();

    	groupDTO.setName(groupe.getName());
    	groupDTO.setReference(groupe.getReference());
       
       
        return groupDTO;
        }
    
    
    private Groupe getGroupeFromDto(GroupDTO groupDTO) {
    	Groupe groupe = new Groupe();

         groupe.setName(groupDTO.getName());
         groupe.setReference(groupDTO.getReference());
         
        return groupe;
    }
    
    private void Update(Groupe GroupeOld ,Groupe GroupeNew) {     
    	
    	GroupeOld.setName(GroupeNew.getName()); 	
    	GroupeOld.setReference(GroupeNew.getReference());
   	
    	groupRepository.save(GroupeOld);
   }
}
