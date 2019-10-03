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

import com.bonheure.controller.dto.GroupeDTO;
import com.bonheure.controller.dto.SocieteDTO;
import com.bonheure.service.GroupeService;

@RestController
@RequestMapping(value="groupes")
public class GroupeController {
	
	@Autowired
	private GroupeService groupeService;
	
	
	
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupeDTO saveGroupe(@RequestBody @Valid GroupeDTO groupe) {

        return groupeService.saveGroupe(groupe);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GroupeDTO getSociete(@RequestParam(required = false) String reference) {
        
        return groupeService.getGroupeByReference(reference);
    }

    
    
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGroupe(@PathVariable(value="reference") String reference ) {
    	groupeService.deleteGroupeByReference(reference);
    }
    
    
    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public GroupeDTO updateGroupe(@PathVariable(value="reference") String reference,@Valid @RequestBody GroupeDTO groupe) {
    	
    	
    	return groupeService.updateGroupeByReference(reference, groupe);
    }

}
