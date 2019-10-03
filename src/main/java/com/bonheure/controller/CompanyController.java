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

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.service.CompanyService;

@RestController
@RequestMapping(value="societes")


public class SocieteController {
	
	@Autowired
    CompanyService companyService;
	
	
	
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public CompanyDTO saveSociete(@RequestBody @Valid CompanyDTO societe) {

	        return companyService.saveSociete(societe);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public CompanyDTO getSociete(@RequestParam(required = false) String reference) {
	        
	        return companyService.getSocieteByReference(reference);
	    }

	    
	    
	    @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteSociete(@PathVariable(value="reference") String reference ) {
         companyService.deleteSocieteByReference(reference);
	    }
	    
	    
	    @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public CompanyDTO updateSociete(@PathVariable(value="reference") String reference, @Valid @RequestBody CompanyDTO societe) {
	    	return companyService.updateSocieteByReference(reference, societe);
	    }
	
}
