package com.bonheure.service;

import com.bonheure.controller.dto.ClientDTO;

import com.bonheure.domain.Client;
import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.ClientRepository;
import com.bonheure.repository.UserRepository;
import com.bonheure.security.JwtTokenProvider;
import com.bonheure.utils.ApiMapper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class ClientService {

	@Autowired
	ClientRepository clientRepository;

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
			return jwtTokenProvider.createToken(email, clientRepository.findByEmail(email).getRole());
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public String saveClient(ClientDTO clientDTO) {

		clientDTO.setReference(UUID.randomUUID().toString());
		Client client = new Client();

		if (!clientRepository.existsByEmail(clientDTO.getEmail())) {
			clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

			client = apiMapper.fromDTOToBean(clientDTO);
			clientRepository.save(client);

			return jwtTokenProvider.createToken(client.getEmail(), client.getRole());
		} else {
			throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	public ClientDTO getClientByReference(String reference) {

		Client client = clientRepository.findOneByReference(reference).orElse(null);

		if (client != null) {

			ClientDTO clientDTO = apiMapper.fromBeanToDTO(client);

			return clientDTO;
		} else {
			throw new CustomException("Client does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	public void deleteClientByReference(String reference) {
		Client client = clientRepository.findOneByReference(reference).orElse(null);

		clientRepository.delete(client);

	}

	public ClientDTO updateClientByReference(String reference, ClientDTO clientDTO) {
		Client oldClient = clientRepository.findOneByReference(reference).orElse(null);

		if (oldClient != null) {
			apiMapper.updateBeanFromDto(clientDTO, oldClient);
			clientRepository.save(oldClient);

		}
		return clientDTO;
	}

}