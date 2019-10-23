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

import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.service.PrestationService;

import io.swagger.annotations.Api;

import com.bonheure.service.PrestationService;

@RestController
@RequestMapping(value = "prestations")
@Api(tags = "prestations")
public class PrestationController {

	@Autowired
	private PrestationService prestationService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PrestationDTO savePrestation(@RequestBody @Valid PrestationDTO prestation) {

		return prestationService.savePrestation(prestation);
	}

	  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public PrestationDTO getPrestation(@RequestParam(required = false) String reference) {

	        return prestationService.getPrestationByReference(reference);
	    }
	  @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public PrestationDTO updateUser(@PathVariable(value = "reference") String reference, @Valid @RequestBody PrestationDTO prestation) {
	        return prestationService.updatePrestationByReference(reference, prestation);
	    }
	
	   
	    @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deletPrestation(@PathVariable(value = "reference") String reference) {
	        prestationService.deletePrestationByReference(reference);
	    }

}
