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

import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.service.ClientService;



@RestController
@RequestMapping(value = "clients")
public class ClientController {

	 @Autowired
	    private ClientService clientService;


	    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ClientDTO saveClient(@RequestBody @Valid ClientDTO client) {

	        return clientService.saveClient(client);
	    }

	    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseStatus(HttpStatus.OK)
	    public ClientDTO saveClient(@RequestParam(required = false) String reference) {

	        return clientService.getClientByReference(reference);
	    }
	    
	    
	    
	    @DeleteMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public void deleteClint(@PathVariable(value="reference") String reference ) {
	    	clientService.deleteClientByReference(reference);
	    }
	    
	    
	    @PutMapping("/{reference}")
	    @ResponseStatus(HttpStatus.OK)
	    public ClientDTO updateClient(@PathVariable(value="reference") String reference,@Valid @RequestBody ClientDTO client) {
	    	return clientService.updateClientByReference(reference, client);
	    }
	
	
	
	
}
