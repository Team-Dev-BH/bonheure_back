package com.bonheure.controller;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.service.UserService;

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
@RequestMapping(value = "users")
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
    
    
    
    
    //
   
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO saveUser(@RequestParam(required = false) String reference) {

        return userService.getUserByReference(reference);
    }


    @DeleteMapping("/{reference}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @ApiOperation(value = "${UserController.delete}")
    @ApiResponses(value = {//
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 403, message = "Access denied"), //
    @ApiResponse(code = 404, message = "The user doesn't exist"), //
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(value = "reference") String reference) {
        userService.deleteUserByReference(reference);
    }

    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable(value = "reference") String reference, @Valid @RequestBody UserDTO user) {
        return userService.updateUserByReference(reference, user);
    }
    
    
    
 
}
   