package com.bonheure.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 
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

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.PrestationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

 

@RestController
@RequestMapping(value = "prestations")
@Api(tags = "prestations")
public class PrestationController {

	@Autowired
	private PrestationService prestationService;

	
	
	//savePrestation
	
	@ResponseStatus(HttpStatus.OK)
    @PostMapping("/savePrestation")
    @ApiOperation(value = "$PrestationController.savePrestation}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    })
	public PrestationDTO savePrestation(@RequestBody @Valid PrestationDTO prestation) {

		return prestationService.savePrestation(prestation);
	}

	//getPrestationByReference
	
	 @GetMapping("/getPrestationByReference")
	    @ApiOperation(value = "${PrestationController.getPrestationByReference}", response = UserDTO.class)
	    @ApiResponses(value = {//
	    @ApiResponse(code = 400, message = "Something went wrong"), //
	    @ApiResponse(code = 403, message = "Access denied"), //
	    @ApiResponse(code = 404, message = "The prestation doesn't exist")})
 	    
	    public PrestationDTO getPrestationByReference(@RequestParam(required = false) String reference) {

	        return prestationService.getPrestationByReference(reference);
	    }
	 
	 
	 
	 
	 //updatePrestationByReference
	 
	/* @PutMapping("/updatePrestationByReference")
     @ApiOperation(value = "${PrestationController.updatePrestationByReference}")
	    public PrestationDTO updateUser(@PathVariable(value = "reference") String reference, @Valid @RequestBody PrestationDTO prestation) {
	        return prestationService.updatePrestationByReference(reference, prestation);
	    }*/
	
	   
	//deletPrestation
     
     @DeleteMapping("/deletePrestationByReference")
     @ApiOperation(value = "${PrestationController.deleteCompanyByReference}")
     @ApiResponses(value = {//
     @ApiResponse(code = 400, message = "Something went wrong"), //
     @ApiResponse(code = 403, message = "Access denied"), //
     @ApiResponse(code = 404, message = "The Prestation doesn't exist")})
	    public void deletePrestationByReference(@PathVariable(value = "reference") String reference) {
	        prestationService.deletePrestationByReference(reference);
	    }
 ////getPrestationFromCategory
     
 	@PostMapping("/prestationByCategoryName")
 	@ApiResponses(value = { //
 			@ApiResponse(code = 400, message = "Something went wrong"), //
 			@ApiResponse(code = 403, message = "Access denied"), //
 	})
 	public List<PrestationDTO> getListPrestationByCategoryName(@RequestBody String categoryName) {
 
 		return prestationService.getListPrestationByCategoryName(categoryName);
 	}
     

     

}
