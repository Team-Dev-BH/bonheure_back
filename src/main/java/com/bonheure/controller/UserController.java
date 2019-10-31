package com.bonheure.controller;

import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.security.JwtResponse;
import com.bonheure.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {

	@Autowired
	private UserService userService;

	// signup user

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/signup")
	@ApiOperation(value = "${UserController.signup}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Email is already in use"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public UserDTO signUpUser(@ApiParam("Signup User") @RequestBody @Valid UserDTO user) {
		return userService.signUpUser(user);
	}

	// signin
	@PostMapping("/signin")
	@ApiOperation(value = "${UserController.signin}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid email/password supplied") })
	public JwtResponse login(//
			@ApiParam("Email") @RequestParam String email, //
			@ApiParam("Password") @RequestParam String password) {
		return userService.signin(email, password);
	}

	// methodeActivate-Desactivate User

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/status")
	@ApiOperation(value = "${UserController.activate}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Email is already in use"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public User activate(@RequestParam(required = false) String reference) {

		return userService.activate(reference);
	}

	// getUserByReference
	@GetMapping("/getUserByReference")
	@ApiOperation(value = "${UserController.getUserByReference}", response = UserDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public UserDTO getUserDetails(@RequestParam(required = false) String reference) {

		return userService.getUserDetails(reference);
	}

//deleteUserByReference
	@DeleteMapping("/deleteUserByReference")
	@ApiOperation(value = "${UserController.deleteUserByReference}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public void deleteUserByReference(@RequestParam(required = false) String reference) {
		userService.deleteUserByReference(reference);
	}

//updateUserByreference
	@PutMapping("/updateUser")
	@ApiOperation(value = "${UserController.updateUser}")
	public UserDTO updateUser(@RequestParam(required = false) String reference, @Valid @RequestBody UserDTO user) {
		return userService.updateUserByReference(reference, user);
	}

	@PostMapping("/password/reset")
	@ApiOperation(value = "${UserController.emailForReset}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid email supplied") })
	public void emailForResetPassword(@ApiParam("Email") @RequestParam String email) {
		userService.requestResetPassword(email);
	}
	// completePasswordReset

	/*
	 * @PostMapping("/reset")
	 * 
	 * @ApiOperation(value = "${UserController.reset}")
	 * 
	 * @ApiResponses(value = {//
	 * 
	 * @ApiResponse(code = 400, message = "Something went wrong"), //
	 * 
	 * @ApiResponse(code = 422, message = "Invalid email supplied")}) public void
	 * resetPassword(@ApiParam("Email") @RequestParam String email) {
	 * userService.resetPassword(email); }
	 */@RequestMapping(value = "password/reset/finish", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Complete Reset Password Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operation Executed Successfully"),
			@ApiResponse(code = 404, message = "User not Found") })
	public UserDTO completePasswordReset(@RequestParam("password") String password, @RequestParam("key") String key) {

		UserDTO user = userService.completePasswordReset(password, key);
		return user;
	}

	@RequestMapping(value = "password/change", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Change Password Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operation Executed Successfully"),
			@ApiResponse(code = 400, message = "Password Don't Match"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	public boolean changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword) {

		boolean result = userService.changePassword(oldPassword, newPassword);

		return result;
	}
	//getLoggedUser
	@GetMapping("/getLoggedUser")
	@ApiOperation(value = "${UserController.getLoggedUser}", response = UserDTO.class)
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public UserDTO getLoggedUser(@RequestParam(required = false) String reference) {

		return userService.getLoggedUser();
	}
}
