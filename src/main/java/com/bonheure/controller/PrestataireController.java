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

import com.bonheure.controller.dto.PrestataireDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.PrestataireService;

@RestController
@RequestMapping(value = "prestataires")
public class PrestataireController {

	@Autowired
	private PrestataireService prestataireService;
	
	  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK) 
	  public PrestataireDTO savePrestataire(@RequestBody @Valid PrestataireDTO prestataire) {

	        return prestataireService.savePrestataire(prestataire);
	    }
	  
	  
	  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public PrestataireDTO getPrestataire(@RequestParam(required = false) String reference) {

	        return prestataireService.getPrestataireByReference(reference);
	    }
	  

	    @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public PrestataireDTO updateUser(@PathVariable(value = "reference") String reference, @Valid @RequestBody PrestataireDTO prestataire) {
	        return prestataireService.updatePrestataireByReference(reference, prestataire);
	    }
	    
	    @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deletPrestataire(@PathVariable(value = "reference") String reference) {
	        prestataireService.deletePrestataireByReference(reference);
	    }

}
