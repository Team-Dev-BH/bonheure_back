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

import com.bonheure.controller.dto.RoleDTO;
import com.bonheure.controller.dto.SocieteDTO;
import com.bonheure.service.SocieteService;

@RestController
@RequestMapping(value="societes")


public class SocieteController {
	
	@Autowired
	SocieteService societeService;
	
	
	
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public SocieteDTO saveSociete(@RequestBody @Valid SocieteDTO societe) {

	        return societeService.saveSociete(societe);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public SocieteDTO getSociete(@RequestParam(required = false) String reference) {
	        
	        return societeService.getSocieteByReference(reference);
	    }

	    
	    
	    @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteSociete(@PathVariable(value="reference") String reference ) {
         societeService.deleteSocieteByReference(reference);
	    }
	    
	    
	    @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public SocieteDTO updateSociete(@PathVariable(value="reference") String reference,@Valid @RequestBody SocieteDTO societe) {
	    	return societeService.updateSocieteByReference(reference, societe);
	    }
	
}
