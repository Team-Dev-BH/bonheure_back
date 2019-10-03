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

import com.bonheure.controller.dto.GroupDTO;
import com.bonheure.service.GroupService;

@RestController
@RequestMapping(value="groupes")
public class GroupeController {
	
	@Autowired
	private GroupService groupService;
	
	
	
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO saveGroupe(@RequestBody @Valid GroupDTO groupe) {

        return groupService.saveGroupe(groupe);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO getSociete(@RequestParam(required = false) String reference) {
        
        return groupService.getGroupeByReference(reference);
    }

    
    
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroupe(@PathVariable(value="reference") String reference ) {
    	groupService.deleteGroupeByReference(reference);
    }
    
    
    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDTO updateGroupe(@PathVariable(value="reference") String reference, @Valid @RequestBody GroupDTO groupe) {
    	
    	
    	return groupService.updateGroupeByReference(reference, groupe);
    }

}
