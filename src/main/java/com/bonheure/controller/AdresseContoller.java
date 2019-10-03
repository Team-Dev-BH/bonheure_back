package com.bonheure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonheure.controller.dto.AdresseDTO;
import com.bonheure.service.AdresseService;

@RestController
@RequestMapping
public class AdresseContoller {

	
	
	 @Autowired
	    private AdresseService adresseService;
	 
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public AdresseDTO saveAdresse(@RequestBody @Valid AdresseDTO adresse) {

	        return adresseService.saveAdresse(adresse);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public AdresseDTO saveAdresse(@RequestParam(required = false) String reference) {

	        return adresseService.getAdresseByReference(reference);
	    }
}
