package com.bonheure.service;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Role;
import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.ClientRepository;
import com.bonheure.repository.PrestataireRepository;
import com.bonheure.repository.UserRepository;
import com.bonheure.security.JwtResponse;
import com.bonheure.security.JwtTokenProvider;
import com.bonheure.security.SecurityUtils;
import com.bonheure.utils.ApiMapper;
import com.bonheure.utils.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.minidev.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

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

	@Autowired
	private MailService mailService;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PrestataireRepository prestataireRepository;

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

	// Activate-Desactivate user

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

	public UserDTO getUserDetails(String reference) {
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

//request reset Passwrd
	public UserDTO requestResetPassword(String mail) {
		return userRepository.findOneByEmail(mail).filter(User::isActivated).map(user -> {

			user.setResetPasswordKey(TokenUtil.generateCode());

			userRepository.save(user);

			UserDTO userDTO = apiMapper.fromBeanToDTO(user);

			mailService.sendForgetPasswordEmail(userDTO, user.getResetPasswordKey());

			return userDTO;
		}).orElseThrow(() -> new CustomException("account not yet activated ", HttpStatus.UNPROCESSABLE_ENTITY));
	}

//	resetPasswrd
	public UserDTO completePasswordReset(String newPassword, String key) {
		logger.debug("Reset user password for reset key {}", key);

		return userRepository.findOneByResetPasswordKey(key).filter(User::isActivated).map(user -> {
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setResetPasswordKey(null);
			userRepository.save(user);
			return apiMapper.fromBeanToDTO(user);
		}).orElseThrow(() -> new CustomException("wrong key", HttpStatus.UNPROCESSABLE_ENTITY));
	}

	static boolean isEmail(String email) {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

//get Current User
	public User getCurrentUser() {

		if (!SecurityUtils.checkIfThereIsUserLogged())
			throw new CustomException("there is already a logged user", HttpStatus.UNPROCESSABLE_ENTITY);

		if (isEmail(SecurityUtils.getCurrentUserLogin())) {
			User user = userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).orElse(null);

			if (user.getRole().getAuthority().equals("CLIENT")) {

				return clientRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin())

						.orElseThrow(() -> new CustomException("user not found ", HttpStatus.UNPROCESSABLE_ENTITY));

			} else if (user.getRole().getAuthority().equals("SUPERADMIN")) {

				return userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin())
						.orElseThrow(() -> new CustomException("user not found ", HttpStatus.UNPROCESSABLE_ENTITY));
			}
		}

		return prestataireRepository.findOneByMobileNumber(SecurityUtils.getCurrentUserLogin())
				.orElseThrow(() -> new CustomException("user not found ", HttpStatus.UNPROCESSABLE_ENTITY));

	}
	
//getLoggedUser
	
	public UserDTO getLoggedUser() {

		if (!SecurityUtils.checkIfThereIsUserLogged())
			throw new CustomException("there is already a logged user", HttpStatus.UNPROCESSABLE_ENTITY);

		if (isEmail(SecurityUtils.getCurrentUserLogin())) {
			User user = userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).orElse(null);


				return userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin()).map(apiMapper::fromBeanToDTO)
						.orElseThrow(() -> new CustomException("user not found ", HttpStatus.UNPROCESSABLE_ENTITY));
			
		}

		return userRepository.findOneByMobileNumber(SecurityUtils.getCurrentUserLogin()).map(apiMapper::fromBeanToDTO)
				.orElseThrow(() -> new CustomException("user not found ", HttpStatus.UNPROCESSABLE_ENTITY));

	}
	//.map(apiMapper::fromBeanToDTO)
	public boolean changePassword(String oldPassword, String newPassword) {
		logger.debug("Reset user password for reset");

		User user = getCurrentUser();
		if (passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
		} else {
			throw new CustomException("user password do not match", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return true;
	}
	
	

}
