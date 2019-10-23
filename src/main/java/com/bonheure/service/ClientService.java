package com.bonheure.service;

import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.controller.dto.CompanyDTO;
import com.bonheure.domain.Client;
import com.bonheure.domain.Company;
import com.bonheure.domain.Role;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.ClientRepository;
import com.bonheure.repository.CompanyRepository;
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
	
    CompanyRepository companyRepository;

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

			if (clientRepository.findByEmail(email).isActivated() == false) {
				throw new CustomException("Account not yet activated ", HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Role authority = clientRepository.findByEmail(email).getRole();

			String jwt = jwtTokenProvider.createToken(email, authority);

			return new JwtResponse(jwt, email, clientRepository.findByEmail(email).getRole().toString());

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	

	
	// signup
		public String saveClient(ClientDTO clientDTO) {

			clientDTO.setReference(UUID.randomUUID().toString());
			Client client = new Client();
			if ((clientRepository.existsByEmail(clientDTO.getEmail()) == false))
					
	
			{
				clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));

				client = apiMapper.fromDTOToBean(clientDTO);
				clientRepository.save(client);

				return jwtTokenProvider.createToken(client.getEmail(), client.getRole());
			} else {
				throw new CustomException("Email in use/code does not exist", HttpStatus.UNPROCESSABLE_ENTITY);
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
			clientDTO.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
			apiMapper.updateBeanFromDto(clientDTO, oldClient);
			clientRepository.save(oldClient);

		}
		return clientDTO;
	}

}