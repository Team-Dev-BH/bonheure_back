package com.bonheure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bonheure.repository.RoleRepository;


import com.bonheure.controller.dto.RoleDTO;
import com.bonheure.service.RoleService;

@RestController
@RequestMapping(value="roles")

   public class RoleController {
	
	@Autowired
	private RoleService roleService;

	
	
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public RoleDTO saveRole(@RequestBody @Valid RoleDTO role) {

	        return roleService.saveRole(role);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public RoleDTO getRole(@RequestParam(required = false) String name) {

	        return roleService.getRoleByName(name);
	    }
	    
	    @DeleteMapping("/{name}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteRole(@PathVariable(value="name") String name ) {
         roleService.deleteRoleByName(name);
	    }
	    
	    
	    @PutMapping("/{name}")
	    @ResponseStatus(HttpStatus.OK)
	    public RoleDTO updateRole(@PathVariable(value="name") String name,@Valid @RequestBody RoleDTO role) {
	    	return roleService.updateRoleByName(name, role);
	    }
	
	
}