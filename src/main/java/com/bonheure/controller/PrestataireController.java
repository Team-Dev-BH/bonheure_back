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
import com.bonheure.security.JwtResponse;
import com.bonheure.service.PrestataireService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "prestataires")
@Api(tags = "prestataires")
public class PrestataireController {

	@Autowired
	private PrestataireService prestataireService;
	
	   //signin
	
    @PostMapping("/signin")
    @ApiOperation(value = "${PrestataireController.signin}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public JwtResponse login(//
        @ApiParam("MobileNumber") @RequestParam String mobileNumber, //
        @ApiParam("Password") @RequestParam String password) {
      return prestataireService.signin(mobileNumber, password);
    }
      
	  //sign up

    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    @ApiOperation(value = "${PrestataireController.signup}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Username is already in use"), //
        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public PrestataireDTO savePrestataire(@ApiParam("Signup Prestataire") @RequestBody @Valid PrestataireDTO prestataire) {
        return prestataireService.signUp(prestataire);
    }

	    
		//getPrestataireByReference
	  
	  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public PrestataireDTO getPrestataire(@RequestParam(required = false) String reference) {

	        return prestataireService.getPrestataireByReference(reference);
	    }
	  

	    @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public PrestataireDTO updatePrestataire(@PathVariable(value = "reference") String reference, @Valid @RequestBody PrestataireDTO prestataire) {
	        return prestataireService.updatePrestataireByReference(reference, prestataire);
	    }
	    
	   /* @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deletPrestataire(@PathVariable(value = "reference") String reference) {
	        prestataireService.deletePrestataireByReference(reference);
	    }*/

}
