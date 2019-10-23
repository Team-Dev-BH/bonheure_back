package com.bonheure.service;

import java.util.UUID;

import javax.validation.constraints.Email;

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
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// signup
		public String savePrestataire(PrestataireDTO prestataireDTO) {

			prestataireDTO.setReference(UUID.randomUUID().toString());
			Prestataire prestataire = new Prestataire();
			if (prestataireRepository.existsByMobileNumber(prestataireDTO.getMobileNumber()) == false) {
				prestataireDTO.setPassword(passwordEncoder.encode(prestataireDTO.getPassword()));

				prestataire = apiMapper.fromDTOToBean(prestataireDTO);
				prestataireRepository.save(prestataire);

				return jwtTokenProvider.createToken(prestataire.getMobileNumber(), prestataire.getRole());
			} else {
				throw new CustomException("MobileNumber is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
			}

		}
		// signin
		public JwtResponse signin(String mobileNumber, String password) {

			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobileNumber, password));

				if (prestataireRepository.findByMobileNumber(mobileNumber).isActivated() == false) {
					throw new CustomException("Account not yet activated ", HttpStatus.UNPROCESSABLE_ENTITY);
				} 
	 
				Role authority = prestataireRepository.findByMobileNumber(mobileNumber).getRole();

				String jwt = jwtTokenProvider.createToken(mobileNumber, authority);

				return new JwtResponse(jwt, mobileNumber, prestataireRepository.findByEmail(mobileNumber).getRole().toString() ); 

			} catch (AuthenticationException e) {
				throw new CustomException("Invalid mobileNumber/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}


	/*public PrestataireDTO savePrestataire(PrestataireDTO prestataireDTO) {

		prestataireDTO.setReference(UUID.randomUUID().toString());

		Prestataire prestataire = apiMapper.fromDTOToBean(prestataireDTO);

		prestataireRepository.save(prestataire);

		return prestataireDTO;
	}*/

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
