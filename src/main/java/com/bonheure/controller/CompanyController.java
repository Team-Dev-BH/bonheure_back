package com.bonheure.controller;

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO saveCompany(@RequestBody @Valid CompanyDTO company) {

        return companyService.saveCompany(company);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO getCompany(@RequestParam(required = false) String reference) {

        return companyService.getCompanyByReference(reference);
    }


     @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable(value = "reference") String reference) {
        companyService.deleteCompanyByReference(reference);
    } 


     @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO updateCompany(@PathVariable(value = "reference") String reference, @Valid @RequestBody CompanyDTO company) {
        return companyService.updateCompanyByReference(reference, company);
    } 

}