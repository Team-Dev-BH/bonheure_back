package com.bonheure.service;

import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Role;
import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.UserRepository;
import com.bonheure.security.JwtResponse;
import com.bonheure.security.JwtTokenProvider;
import com.bonheure.utils.ApiMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	// signin
	public JwtResponse signin(String email, String password) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

// for superadmin 
			userRepository.findByEmail(email).setActivated(true);
			
			Role authority = userRepository.findByEmail(email).getRole();

			String jwt = jwtTokenProvider.createToken(email, authority);

			return new JwtResponse(jwt, email, userRepository.findByEmail(email).getRole().toString());

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// signup
	
	public UserDTO signUpUser(UserDTO userDTO) {

		userDTO.setReference(UUID.randomUUID().toString());
		User user = new User();

		if (userRepository.existsByEmail(userDTO.getEmail()) == false) {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

			user = apiMapper.fromDTOToBean(userDTO);
			userRepository.save(user);

			return userDTO;
		} else {
			throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// methodeActivate-Desactivate user

	public User activate(String reference) {

		User user = userRepository.findOneByReference(reference).orElse(null);

		if (!user.isActivated()) {
			user.setActivated(true);
			user.setActivationDate(LocalDateTime.now());
		} else {
			user.setActivated(false);
		}
		userRepository.save(user);
		return user;

	}

	// getUserByReference

	public UserDTO getUserByReference(String reference) {
		User user = userRepository.findOneByReference(reference).orElse(null);

		if (user == null)
			return null;
		UserDTO userDTO = apiMapper.fromBeanToDTO(user);

		return userDTO;
	}

	// deleteUserByreference

	public void deleteUserByReference(String reference) {
		
		User user = userRepository.findOneByReference(reference).orElse(null);
		
		
		userRepository.delete(user);

	}

	// updateUserByreference

	public UserDTO updateUserByReference(String reference, UserDTO userDTO) {

		User oldUser = userRepository.findOneByReference(reference).orElse(null);

		if (oldUser != null) {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			apiMapper.updateBeanFromDto(userDTO, oldUser);
			userRepository.save(oldUser);
		}
		return userDTO;
	}

}
