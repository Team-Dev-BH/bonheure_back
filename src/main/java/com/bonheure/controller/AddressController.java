package com.bonheure.controller;

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO saveAddress(@RequestBody @Valid AddressDTO adresse) {

        return addressService.saveAddress(adresse);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO saveAddress(@RequestParam(required = false) String reference) {

        return addressService.getAddressByReference(reference);
    }
}
