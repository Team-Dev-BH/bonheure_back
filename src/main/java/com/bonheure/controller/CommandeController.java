package com.bonheure.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bonheure.controller.dto.CommandeDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.CommandeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "commandes")
@Api(tags = "commandes")

public class CommandeController {
	
	@Autowired
	CommandeService commandeService;
	//post
	 @ResponseStatus(HttpStatus.OK)
	    @PostMapping("/save")
	    @ApiOperation(value = "${CommandeController.save}")
	    @ApiResponses(value = {//
	        @ApiResponse(code = 400, message = "Something went wrong"), //
	        @ApiResponse(code = 403, message = "Access denied"), //
	        @ApiResponse(code = 422, message = "Username is already in use"), //
	        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	    public CommandeDTO saveCommande(@RequestBody @Valid CommandeDTO commande) {

	        return commandeService.saveCommande(commande);
	    }


		//getAllCommandes
	     @GetMapping("/getAll")
	     @ApiOperation(value = "${CommandeController.getAll}", response = CommandeDTO.class)
	     @ApiResponses(value = {//
	     @ApiResponse(code = 400, message = "Something went wrong"), //
	     @ApiResponse(code = 403, message = "Access denied"), //
	     @ApiResponse(code = 404, message = "The user doesn't exist"), //
	     @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	     public List<CommandeDTO> getAllCommande(){
	    	                   
	        return commandeService.getAllCommande();
	    }
	     
	     //updateCommande
	     @PutMapping("/updateCommande")
	     @ApiOperation(value = "${UserController.updateCommande}")
	     public CommandeDTO updateCommande(@RequestParam(required = false) String reference, @Valid @RequestBody CommandeDTO commande) {
	         return commandeService.updateCommandeByRefrence(reference, commande);
	     }
	     
	    
}
