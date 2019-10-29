package com.bonheure.controller;

import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "companies")
@Api(tags = "companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

//PostCompanny
    
    
        @ResponseStatus(HttpStatus.OK)
        @PostMapping("/saveCompany")
        @ApiOperation(value = "${CompanyController.saveCompany}")
        @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Company is already in use")})
    public CompanyDTO saveCompany(@RequestBody @Valid CompanyDTO company) {

        return companyService.saveCompany(company);
    }
        
      //GetCompany 
    @GetMapping("/getCompanyByReference")
    @ApiOperation(value = "${CompanyController.getCompanyByReference}", response = UserDTO.class)
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The company doesn't exist")})
    public CompanyDTO getCompanyByReference(@RequestParam(required = false) String reference) {

        return companyService.getCompanyByReference(reference);
    }

//deleteCompany
      
     @DeleteMapping("/deleteCompanyByReference")
     @ApiOperation(value = "${CompanyController.deleteCompanyByReference}")
     @ApiResponses(value = {//
     @ApiResponse(code = 400, message = "Something went wrong"), //
     @ApiResponse(code = 403, message = "Access denied"), //
     @ApiResponse(code = 404, message = "The Company doesn't exist")})
    public void deleteCompanyByReference(@PathVariable(value = "reference") String reference) {
        companyService.deleteCompanyByReference(reference);
    } 

//updateCompanyByReference
     
     @PutMapping("/updateCompanyByReference")
     @ApiOperation(value = "${CompanyController.updateCompanyByReference}")
    public CompanyDTO updateCompany(@PathVariable(value = "reference") String reference, @Valid @RequestBody CompanyDTO company) {
        return companyService.updateCompanyByReference(reference, company);
    } 

}