package com.bonheure.controller;

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "address")
@Api(tags = "address")
public class AddressController {
// test Adress

    @Autowired
    private AddressService addressService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Email is already in use"), //
        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public AddressDTO saveAddress(@RequestBody @Valid AddressDTO adresse) {

        return addressService.saveAddress(adresse);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO saveAddress(@RequestParam(required = false) String reference) {

        return addressService.getAddressByReference(reference);
    }
    
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable(value = "reference") String reference) {
    	addressService.deleteAddressByReference(reference);
    }

    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO updateAddress(@PathVariable(value = "reference") String reference, @Valid @RequestBody AddressDTO adresse) {
        return addressService.updateAddressByReference(reference, adresse);
    }
    
    
  
    
}
