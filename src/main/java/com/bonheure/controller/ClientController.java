package com.bonheure.controller;

import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.security.JwtResponse;
import com.bonheure.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "clients")
@Api(tags = "clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	// signUp
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/signup")
	@ApiOperation(value = "${ClientController.signup}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public String saveClient(@RequestBody @Valid ClientDTO client) {

		return clientService.signUp(client);
	}

	// signin
	@PostMapping("/signin")
	@ApiOperation(value = "${ClientController.signin}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid username/password supplied") })
	public JwtResponse login(//
			@ApiParam("Email") @RequestParam String email, //
			@ApiParam("Password") @RequestParam String password) {
		return clientService.signin(email, password);
	}

	// getByReference
	@GetMapping("/getClientByReference")
	///@PreAuthorize("hasRole('ROLE_ADMIN')") *************************
	@ApiOperation(value = "${ClientController.getClientByReference}", response = ClientDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })

	public ClientDTO getClientByReference(@RequestParam(required = false) String reference) {

		return clientService.getClientByReference(reference);
	}

	// updateClientByreference
	@PutMapping("/updateClient")
	@ApiOperation(value = "${ClientController.updateClient}")
	public ClientDTO updateUser(@RequestParam(required = false) String reference,
			@Valid @RequestBody ClientDTO client) {
		return clientService.updateClientByReference(reference, client);
	}

}