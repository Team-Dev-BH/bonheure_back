package com.bonheure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.RoleDTO;
import com.bonheure.domain.Role;
import com.bonheure.repository.RoleRepository;

@Service

public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	
    public RoleDTO saveRole(RoleDTO roleDTO)
    {
        Role role = getRoleFromDto(roleDTO);

     roleRepository.save(role);

        return roleDTO;

    }

    public RoleDTO getRoleByName(String name)
    {
        Role role =roleRepository.findByName(name);

       RoleDTO roleDTO =  getRoleDTOFromRole(role);

        return roleDTO;
    }

    
    
   
  
    
    
    public void  deleteRoleByName(String name)
    {
    	Role role =roleRepository.findByName(name);
        roleRepository.delete(role);
    
    }
    
    
    public  RoleDTO updateRoleByName(String name,RoleDTO roleDTO)
    {
    	 Role roleOld = roleRepository.findByName(name);
    	 Role roleNew = getRoleFromDto(roleDTO);
    	 
    	if ( roleOld != null )
    	{
    		Update(roleOld,roleNew);
           
    	}

        return roleDTO;

    }
    
    
    
    
    
    
    
    
    private RoleDTO getRoleDTOFromRole(Role role) {

        RoleDTO roleDTO= new RoleDTO();

        roleDTO.setName(role.getName());
        
       
       
        return roleDTO;   
        }
    
    
    private Role getRoleFromDto(RoleDTO roleDTO) {
        Role role = new Role();

          role.setName(roleDTO.getName());

        return role;
    }
    
    
    
    
    
    private void Update(Role RoleOld ,Role RoleNew) {     
    	
         RoleOld.setName(RoleNew.getName());  
    	
    	 roleRepository.save(RoleOld);
    }
}
