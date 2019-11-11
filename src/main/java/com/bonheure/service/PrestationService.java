package com.bonheure.service;



import java.util.HashSet;
import java.util.List;
import java.util.Set;


import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.controller.dto.PrestationDTO;
import com.bonheure.domain.Category;
import com.bonheure.domain.Client;
import com.bonheure.domain.Group;

import com.bonheure.domain.Prestation;
import com.bonheure.domain.User;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.Prestation;
import com.bonheure.domain.Prestation;
import com.bonheure.repository.ClientRepository;
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

	@Autowired
	private UserService userService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientService clientService;

	public PrestationDTO savePrestation(PrestationDTO prestationDTO) {

		prestationDTO.setReference(UUID.randomUUID().toString());

		Prestation prestation = apiMapper.fromDTOToBean(prestationDTO);

		prestationRepository.save(prestation);

		return prestationDTO;
	}

	public PrestationDTO getPrestationByReference(String reference) {
		Prestation prestation = prestationRepository.findOneByReference(reference).orElse(null);

		if (prestation == null)
			return null;
		PrestationDTO prestationDTO = apiMapper.fromBeanToDTO(prestation);

		return prestationDTO;
	}

	public PrestationDTO updatePrestationByReference(String reference, PrestationDTO prestationDTO) {

		Prestation oldPrestation = prestationRepository.findOneByReference(reference).orElse(null);

		if (oldPrestation != null) {
			apiMapper.updateBeanFromDto(prestationDTO, oldPrestation);
			prestationRepository.save(oldPrestation);
		}

		return prestationDTO;

	}

	// getPrestationByName
	public PrestationDTO getPrestationByName(String name) {

		Prestation prestation = prestationRepository.findOneByName(name).orElse(null);
		if (prestation == null)
			return null;
		PrestationDTO prestationDTO = apiMapper.fromBeanToDTO(prestation);

		return prestationDTO;

	}

	// deletePrestationByReference
	public void deletePrestationByReference(String reference) {
		Prestation prestation = prestationRepository.findOneByReference(reference).orElse(null);

		prestationRepository.delete(prestation);

	}

	public Set<Prestation> getPrestationsByClient(Client client) {

		Set<Group> groups = client.getGroups();

		Set<Prestation> prestations = new HashSet<>();

		for (Group group : groups) {

			for (Prestation prestation : group.getPrestations()) {

				prestations.add(prestation);

			}

		}
		return prestations;
	}

	public Set<Prestation> getPrestationsByCategory(String categoryName, Set<Prestation> allPrestations) {

		Set<Prestation> prestations = new HashSet<>();

		for (Prestation prestation : allPrestations) {

			Set<Category> categories = prestation.getCategories();

			for (Category category : categories) {

				if (category.getName().equals(categoryName)) {

					prestations.add(prestation);

				}
			}

		}
		return prestations;

	}

	public Set<PrestationDTO> getPrestationsByCategoryNameForCurrentUser(String categoryName) {

		Client client = clientService.getClientFromUser(userService.getCurrentUser().getReference());

		Set<Prestation> allPrestations = getPrestationsByClient(client);

		Set<Prestation> prestations = getPrestationsByCategory(categoryName, allPrestations);
		Set<PrestationDTO> prestationDTOs = new HashSet<>();
		prestations.forEach(prestation -> prestationDTOs.add(apiMapper.fromBeanToDTO(prestation)));
		return prestationDTOs;

	}

	public Set<PrestationDTO> getAllPrestations() {

		return prestationRepository.findAll().stream().map(apiMapper::fromBeanToDTO).collect(Collectors.toSet());
	}

	// prestation by categorie name:
	public List<PrestationDTO> getListPrestationByCategoryName(String categoryName) {

		Category category = categoryRepository.findOneByName(categoryName).orElse(null);

		List<PrestationDTO> prestationDTOs = category.getPrestations().stream()
				.map(prestation -> apiMapper.fromBeanToDTO(prestation))
				.filter(prestationDTO -> prestationDTO.getParentReference() == null).collect(Collectors.toList());

		return prestationDTOs;
	}

    //prestation by parent name :
    public List<PrestationDTO> getListPrestationByParentName(String ParentName) {


        List<Prestation> prestations = prestationRepository.findAll().stream()
				.filter(prestation -> (prestation.getParent()!= null)&&(prestation.getParent().getName().equals(ParentName)))
                .collect(Collectors.toList());//


		return  prestations.stream().map(apiMapper::fromBeanToDTO).collect(Collectors.toList());


    }
}
