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

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.service.AddressService;

@RestController
@RequestMapping
public class AdresseContoller {

	
	
	 @Autowired
	    private AddressService addressService;
	 
	 @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public AddressDTO saveAdresse(@RequestBody @Valid AddressDTO adresse) {

	        return addressService.saveAdresse(adresse);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public AddressDTO saveAdresse(@RequestParam(required = false) String reference) {

	        return addressService.getAdresseByReference(reference);
	    }
}
