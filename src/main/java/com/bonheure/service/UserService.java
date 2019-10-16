package com.bonheure.service;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.UserRepository;
import com.bonheure.security.JwtTokenProvider;
import com.bonheure.utils.ApiMapper;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ApiMapper apiMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    public String signin(String email, String password) {
        try {
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
          return jwtTokenProvider.createToken(email,userRepository.findByEmail(email).getRole());
        } catch (AuthenticationException e) {
          throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
      }
    
    
    
    
    
    
    
    
    //
    
  

    public String saveUser(UserDTO userDTO) {

        userDTO.setReference(UUID.randomUUID().toString());
        User user =new User();
        if (!userRepository.existsByUsername(userDTO.getUsername())) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        user = apiMapper.fromDTOToBean(userDTO);
        userRepository.save(user);

        return  jwtTokenProvider.createToken(user.getUsername(), user.getRole());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
          }

    }
    

    public UserDTO getUserByReference(String reference) {
        User user = userRepository.findOneByReference(reference).
                orElse(null);

        if (user == null)
            return null;
        UserDTO userDTO = apiMapper.fromBeanToDTO(user);

        return userDTO;
    }
    
    


    public void deleteUserByReference(String reference) {
        User user = userRepository.findOneByReference(reference).
                orElse(null);


        userRepository.delete(user);

    }

    public UserDTO updateUserByReference(String reference, UserDTO userDTO) {

        //TODO throw exception if not found
        User oldUser = userRepository.findOneByReference(reference).orElse(null);
       

        if (oldUser != null) {
            apiMapper.updateBeanFromDto(userDTO, oldUser);
            userRepository.save(oldUser);
        }
        return userDTO;
    }
    
    
    
    
    


}
