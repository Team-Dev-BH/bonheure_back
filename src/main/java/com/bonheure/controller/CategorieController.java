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

import com.bonheure.controller.dto.CategorieDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.CategorieService;


@RestController
@RequestMapping
public class CategorieController {

	@Autowired
	private CategorieService categorieService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategorieDTO saveCategorie(@RequestBody @Valid CategorieDTO categorie) {

        return categorieService.saveCategorie(categorie);
    }
		
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategorieDTO getCategorie(@RequestParam(required = false) String reference) {

        return categorieService.getCategByReference(reference);
    }
		
		
	
		
	
	
}
