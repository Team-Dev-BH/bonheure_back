package com.bonheure.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.PrestataireDTO;
import com.bonheure.domain.Prestataire;
import com.bonheure.domain.Role;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.PrestataireRepository;
import com.bonheure.security.JwtResponse;
import com.bonheure.security.JwtTokenProvider;
import com.bonheure.utils.ApiMapper;

@Service
public class PrestataireService {
	@Autowired
	PrestataireRepository prestataireRepository;

	@Autowired
	private ApiMapper apiMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public PrestataireDTO signUp(PrestataireDTO prestataireDTO) {

		prestataireDTO.setReference(UUID.randomUUID().toString());
		Prestataire prestataire = new Prestataire();
		if (prestataireRepository.existsByMobileNumber(prestataireDTO.getMobileNumber()) == false) {
			prestataireDTO.setPassword(passwordEncoder.encode(prestataireDTO.getPassword()));
			prestataireDTO.setActivated(false);
			prestataire = apiMapper.fromDTOToBean(prestataireDTO);
			prestataireRepository.save(prestataire);
			return prestataireDTO;
		} else {
			throw new CustomException("MobileNumber is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	public JwtResponse signin(String mobileNumber, String password) {

		try {
			Prestataire prestataire = prestataireRepository.findOneByMobileNumber(mobileNumber).orElse(null);
			if (prestataire.isActivated() == false) {
				throw new CustomException("Account not yet activated ", HttpStatus.UNPROCESSABLE_ENTITY);
			}

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobileNumber, password));

			Role authority = prestataire.getRole();

			String jwt = jwtTokenProvider.createTokenForPrestataire(mobileNumber, authority);

			return new JwtResponse(jwt, mobileNumber, prestataire.getRole().getAuthority());

		} catch (AuthenticationException e) {
			throw new CustomException("Invalid email/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public PrestataireDTO getPrestataireByReference(String reference) {
		Prestataire prestataire = prestataireRepository.findOneByReference(reference).orElse(null);

		if (prestataire == null)
			return null;
		PrestataireDTO prestataireDTO = apiMapper.fromBeanToDTO(prestataire);

		return prestataireDTO;
	}

	public PrestataireDTO updatePrestataireByReference(String reference, PrestataireDTO prestataireDTO) {

		Prestataire oldPrestataire = prestataireRepository.findOneByReference(reference).orElse(null);

		if (oldPrestataire != null) {
			apiMapper.updateBeanFromDto(prestataireDTO, oldPrestataire);
			prestataireRepository.save(oldPrestataire);
		}

		return prestataireDTO;

	}

	public void deletePrestataireByReference(String reference) {
		Prestataire prestataire = prestataireRepository.findOneByReference(reference).orElse(null);

		prestataireRepository.delete(prestataire);

	}
}
