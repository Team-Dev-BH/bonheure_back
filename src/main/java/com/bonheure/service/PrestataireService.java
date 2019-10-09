package com.bonheure.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.PrestataireDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Prestataire;
import com.bonheure.domain.User;
import com.bonheure.repository.PrestataireRepository;
import com.bonheure.utils.ApiMapper;

@Service
public class PrestataireService {

	@Autowired
	PrestataireRepository prestataireRepository;

	@Autowired
	private ApiMapper apiMapper;

	public PrestataireDTO savePrestataire(PrestataireDTO prestataireDTO) {

		prestataireDTO.setReference(UUID.randomUUID().toString());

		Prestataire prestataire = apiMapper.fromDTOToBean(prestataireDTO);

		prestataireRepository.save(prestataire);

		return prestataireDTO;
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
