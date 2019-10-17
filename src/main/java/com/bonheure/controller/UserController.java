package com.bonheure.controller;


import com.bonheure.controller.dto.UserDTO;
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
    
     


     
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signup")
    @ApiOperation(value = "${UserController.signup}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Username is already in use"), //
        @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String saveUser(@ApiParam("Signup User") @RequestBody @Valid UserDTO user) {
        return userService.saveUser(user);
    }
    
    
    
    @PostMapping("/signin")
    @ApiOperation(value = "${UserController.signin}")
    @ApiResponses(value = {//
  @ApiResponse(code = 400, message = "Something went wrong"), //
  @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    
    
    public String login(//
        @ApiParam("Email") @RequestParam String email, //
        @ApiParam("Password") @RequestParam String password) {
      return userService.signin(email, password);
    }
    
    
    
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

    @PutMapping("/updateUser")
    @ApiOperation(value = "${UserController.updateUser}")
    public UserDTO updateUser(@PathVariable(value = "reference") String reference, @Valid @RequestBody UserDTO user) {
        return userService.updateUserByReference(reference, user);
    }
    
    
    
 
}
   