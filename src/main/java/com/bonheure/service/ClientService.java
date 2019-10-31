package com.bonheure.service;

import com.bonheure.controller.dto.ClientDTO;

import com.bonheure.domain.Client;
import com.bonheure.domain.Company;
import com.bonheure.domain.Role;
import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.ClientRepository;
import com.bonheure.repository.CompanyRepository;
import com.bonheure.repository.UserRepository;
import com.bonheure.security.JwtResponse;
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

	@Autowired
	private CompanyRepository companyRepository;

	// signin
	public JwtResponse signin(String email, String password) {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
			Client client = clientRepository.findOneByEmail(email).orElse(null);
			
			Role authority = client.getRole();

			String jwt = jwtTokenProvider.createToken(email, authority);

			return new JwtResponse(jwt, email, client.getRole().toString());

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// company exists by domain
	public Company verifyCompany(ClientDTO client) {

		String domain = client.getEmail().substring(client.getEmail().indexOf("@") + 1);

		Company company = companyRepository.findOneByDomainName(domain).orElse(null);

		if (company == null)
			return null;

		return company;

	}

	// signup
	public String signUp(ClientDTO clientDTO) {

		clientDTO.setReference(UUID.randomUUID().toString());
		Client client = new Client();

		if (!clientRepository.existsByEmail(clientDTO.getEmail())) {

			if (verifyCompany(clientDTO) == null) {
				throw new CustomException("No company for this client is found ", HttpStatus.UNPROCESSABLE_ENTITY);
			}

			if (!verifyCompany(clientDTO).getCode().equals(clientDTO.getCompanyCode())) {
				throw new CustomException("your code is wrong ", HttpStatus.UNPROCESSABLE_ENTITY);
			}

			clientDTO.setCompanyReference(verifyCompany(clientDTO).getReference());

			clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

			clientDTO.setActivated(true);

			client = apiMapper.fromDTOToBean(clientDTO);

			clientRepository.save(client);

			return jwtTokenProvider.createToken(client.getEmail(), client.getRole());
		} else {
			throw new CustomException("you are already registered", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// getByReference

	public ClientDTO getClientByReference(String reference) {

		Client client = clientRepository.findOneByReference(reference).orElse(null);

		if ((client != null)) {

			ClientDTO clientDTO = apiMapper.fromBeanToDTO(client);

			return clientDTO;
		} else {
			throw new CustomException("Client does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

//update
	public ClientDTO updateClientByReference(String reference, ClientDTO clientDTO) {

		Client oldClient = clientRepository.findOneByReference(reference).orElse(null);

		if (oldClient != null) {

			clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

			apiMapper.updateBeanFromDto(clientDTO, oldClient);

			clientRepository.save(oldClient);
		}
		return clientDTO;
	}

}