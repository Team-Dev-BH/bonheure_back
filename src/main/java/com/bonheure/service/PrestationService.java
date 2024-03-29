package com.bonheure.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.domain.Category;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.Prestation;
import com.bonheure.repository.CategoryRepository;
import com.bonheure.repository.PrestationRepository;
import com.bonheure.utils.ApiMapper;

@Service
public class PrestationService {

	@Autowired
	PrestationRepository prestationRepository;

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	private ApiMapper apiMapper;
	
	//savePrestation
	
	public PrestationDTO savePrestation(PrestationDTO prestationDTO) {

		prestationDTO.setReference(UUID.randomUUID().toString());

		Prestation prestation = apiMapper.fromDTOToBean(prestationDTO);

		prestationRepository.save(prestation);

		return prestationDTO;
	}
	
	//getPrestationByReference
	
	public PrestationDTO getPrestationByReference(String reference) {
		Prestation prestation = prestationRepository.findOneByReference(reference).orElse(null);

		if (prestation == null)
			return null;
		PrestationDTO prestationDTO = apiMapper.fromBeanToDTO(prestation);

		return prestationDTO;
	}
	
	//updatePrestationByReference
	
	/*public PrestationDTO updatePrestationByReference(String reference, PrestationDTO prestationDTO) {

		Prestation oldPrestation = prestationRepository.findOneByReference(reference).orElse(null);

		if (oldPrestation != null) {
			apiMapper.updateBeanFromDto(prestationDTO, oldPrestation);
			prestationRepository.save(oldPrestation);
		}

		return prestationDTO;

	}*/
	
	//deletePrestationByReference
	
	public void deletePrestationByReference(String reference) {
		Prestation prestation = prestationRepository.findOneByReference(reference).orElse(null);

		prestationRepository.delete(prestation);

	}
	
	public List<PrestationDTO> getListPrestationByCategoryName(String categoryName) {

		Category  category = categoryRepository.findOneByName(categoryName).orElse(null);

		List<PrestationDTO>  prestationDTOs = category.getPrestations().stream().map(prestation -> apiMapper.fromBeanToDTO(prestation)).collect(Collectors.toList());
	
		return prestationDTOs;
	}
	
	
}
