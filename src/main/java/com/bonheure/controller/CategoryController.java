package com.bonheure.controller;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.WorkingAreaDTO;
import com.bonheure.service.CategoryService;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "categories")
@Api(tags = "categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO saveCategorie(@RequestBody @Valid CategoryDTO categorie) {

        return categoryService.saveCategory(categorie);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategorie(@RequestParam(required = false) String reference) {

        return categoryService.getCategoryByReference(reference);
    }

    

    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategorie(@PathVariable(value = "reference") String reference) {
    	categoryService.deleteCategoryByReference(reference);
    }

    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategorie(@PathVariable(value = "reference") String reference, @Valid @RequestBody CategoryDTO categorie) {
        return categoryService.updateCategoryByReference(reference, categorie);
    }
	

}
