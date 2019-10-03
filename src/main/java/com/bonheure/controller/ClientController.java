package com.bonheure.controller;

import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public void deleteClint(@PathVariable(value = "reference") String reference) {
        clientService.deleteClientByReference(reference);
    }


    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable(value = "reference") String reference, @Valid @RequestBody ClientDTO client) {
        return clientService.updateClientByReference(reference, client);
    }


}
