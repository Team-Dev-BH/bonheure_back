package com.bonheure.controller;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.repository.UserRepository;
import com.bonheure.service.UserService;
import com.sun.tools.sjavac.Log;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO saveUser(@RequestBody @Valid UserDTO user) {

        return userService.saveUser(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO saveUser(@RequestParam(required = false) String reference) {

        return userService.getUserByReference(reference);
    }
    
    
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable(value="reference") String reference ) {
    	userService.deleteUserByReference(reference);		          
    }
    
    @PutMapping("/{reference}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@PathVariable(value="reference") String reference,@Valid @RequestBody UserDTO user) {
    	return userService.updateUserByReference(reference, user);
    }
    
    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    
    public UserDTO getUser(@PathVariable(value="firstName") String firstName) {
    	
    	return  userService.getUserByFirstName(firstName);
    	
    }
    
    @GetMapping("/{creationDate}")
    @ResponseStatus(HttpStatus.OK)
    
    public UserDTO getUser(@PathVariable(value ="creationDate" )LocalDateTime  creationDate ) {
    	
    	return userService.getUserByCreationDate(creationDate);
    }
}
   