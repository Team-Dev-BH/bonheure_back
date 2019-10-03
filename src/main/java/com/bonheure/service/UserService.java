package com.bonheure.service;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.repository.RoleRepository;
import com.bonheure.repository.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional; 


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;




    public UserDTO saveUser(UserDTO userDTO)
    {
        User user = getUserFromDto(userDTO);

        userRepository.save(user);

        return userDTO;

    }

    public UserDTO getUserByReference(String reference)
    {
        User user = userRepository.findByReference(reference);

        UserDTO userDTO =  getUserDTOFromUser(user);

        return userDTO;
    }
    

    public void  deleteUserByReference(String reference)
       {
       	User user =userRepository.findByReference(reference);
        userRepository.delete(user);
       
       }
    
    public  UserDTO updateUserByReference(String reference,UserDTO userDTO)
    {
    	 User userOld = userRepository.findByReference(reference);
    	 User userNew = getUserFromDto(userDTO);
    	 
    	if ( userOld != null )
    	{
    		Update(userOld,userNew);
    	}
        return userDTO;
    }

    
    public UserDTO getUserByFirstName(String firstName)
    {
    	
    	User user = userRepository.findByFirstName(firstName);
    	userRepository.save(user);
    	UserDTO userDTO= getUserDTOFromUser(user);
        return userDTO;
    	
    }

    public UserDTO getUserByCreationDate(LocalDateTime creationDate)
    {
    	User user = userRepository.findByCreationDate(creationDate)   ;
    	userRepository.save(user);
    	UserDTO userDTO= getUserDTOFromUser(user);
        return userDTO;
    	
    	
    }
    
    
    
    private UserDTO getUserDTOFromUser(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setActivated(user.getActivated());
        userDTO.setEmail(user.getEmail());
        userDTO.setActivationDate(user.getActivationDate());
        userDTO.setCreationDate(user.getCreationDate());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setModificationDate(user.getModificationDate());
        userDTO.setPassword(user.getPassword());
        userDTO.setReference(user.getReference());
		userDTO.setRole(user.getRole().getName());
       
        

        return userDTO;    }

    private User getUserFromDto(UserDTO userDTO) {
        User user = new User();

        user.setActivated(userDTO.getActivated());
        user.setEmail(userDTO.getEmail());
        user.setActivationDate(userDTO.getActivationDate());
        user.setCreationDate(userDTO.getCreationDate());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setModificationDate(userDTO.getModificationDate());
        user.setPassword(userDTO.getPassword());
        user.setReference(userDTO.getReference());
		user.setRole(roleRepository.findByName(userDTO.getRole()));

        return user;
    }

    
    private void Update(User UserOld ,User UserNew) {     
    	UserOld.setActivated(UserNew.getActivated());
    	UserOld.setEmail(UserNew.getEmail());
    	UserOld.setActivationDate(UserNew.getActivationDate());
    	UserOld.setCreationDate(UserNew.getCreationDate());
    	UserOld.setFirstName(UserNew.getFirstName());
    	UserOld.setLastName(UserNew.getLastName());
    	UserOld.setMobileNumber(UserNew.getMobileNumber());
    	UserOld.setModificationDate(UserNew.getModificationDate());
    	UserOld.setPassword(UserNew.getPassword());
    	UserOld.setRole(UserNew.getRole());
    	
    	  userRepository.save(UserOld);
    }

}
