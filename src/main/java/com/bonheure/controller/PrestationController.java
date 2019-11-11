package com.bonheure.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.UserDTO;

import com.bonheure.domain.Prestation;
import com.bonheure.service.PrestationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

 

@RestController
@RequestMapping(value = "prestations")
@Api(tags = "prestations")
@CrossOrigin("*")
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


     //get prestation by name
	@GetMapping("/getPrestationByName")
	@ApiOperation(value = "${PrestationController.getPrestationByName}", response = UserDTO.class)
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The prestation doesn't exist")})

	public PrestationDTO getPrestationByName(@RequestParam(required = false) String name) {

		return prestationService.getPrestationByName(name);
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
     
 	@GetMapping("/prestationByCategoryName")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
	})
	public List<PrestationDTO> getListPrestationByCategoryName(@RequestParam String categoryName) {

		return prestationService.getListPrestationByCategoryName(categoryName);
	}


	//getListPrestationByParentName

	@GetMapping("/prestationByParentName")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
	})
	public List<PrestationDTO> getListPrestationByParentName(@RequestParam String ParentName) {

		return prestationService.getListPrestationByParentName(ParentName);

	}

	@DeleteMapping("/{reference}")
	@ResponseStatus(HttpStatus.OK)
	public void deletPrestation(@PathVariable(value = "reference") String reference) {
		prestationService.deletePrestationByReference(reference);
	}

	// getPrestationsByCategoryNameForCurrentUser

	@GetMapping("/getPrestationsByCategoryName")
	@ApiOperation(value = "${PrestationController.getPrestationsByCategoryName}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "Category doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public Set<PrestationDTO> getPrestationsByCategoryName(@RequestParam(required = false) String categoryName) {

		return prestationService.getPrestationsByCategoryNameForCurrentUser(categoryName);
	}

	@GetMapping("/getAll")
	@ApiOperation(value = "${PrestationController.getAll}", response = PrestationDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "Category doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public Set<PrestationDTO> getAllPrestations() {
		return prestationService.getAllPrestations();
	}
}
