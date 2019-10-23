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
 
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {


    @Autowired
    private UserService userService;
 
    
    //signup user
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Email is already in use"), //
        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public UserDTO signUpUser(@ApiParam("Signup User") @RequestBody @Valid UserDTO user) {
        return userService.signUpUser(user);
    }
    
    
    //signin
    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 422, message = "Invalid email/password supplied")})
    public JwtResponse login(//
        @ApiParam("Email") @RequestParam String email, //
        @ApiParam("Password") @RequestParam String password) {
      return userService.signin(email, password);
    }
    
    
    //methodeActivate-Desactivate User 
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/status")
    @ApiOperation(value = "${UserController.activate}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Email is already in use"), //
        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public User activate(@RequestParam(required = false) String reference) {
    	
        return userService.activate(reference);
    }
     
    //getUserByReference
     @GetMapping("/getUserByReference")
     @ApiOperation(value = "${UserController.getUserByReference}", response = UserDTO.class)
     @ApiResponses(value = {//
     @ApiResponse(code = 400, message = "Something went wrong"), //
     @ApiResponse(code = 403, message = "Access denied"), //
     @ApiResponse(code = 404, message = "The user doesn't exist"), //
     @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
     public UserDTO getUserByReference(@RequestParam(required = false) String reference){
    	                   
        return userService.getUserByReference(reference);
    }

//deleteUserByReference
    @DeleteMapping("/deleteUserByReference")
    @ApiOperation(value = "${UserController.deleteUserByReference}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The user doesn't exist"), //
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void deleteUserByReference(@RequestParam(required = false) String reference) {
        userService.deleteUserByReference(reference);
    }
//updateUserByreference
    @PutMapping("/updateUser")
    @ApiOperation(value = "${UserController.updateUser}")
    public UserDTO updateUser(@RequestParam(required = false) String reference, @Valid @RequestBody UserDTO user) {
        return userService.updateUserByReference(reference, user);
    }
    
    
    
 
}
   