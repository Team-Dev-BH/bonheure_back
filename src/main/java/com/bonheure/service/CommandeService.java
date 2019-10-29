package com.bonheure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bonheure.controller.dto.CommandeDTO;
import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.Commande;
import com.bonheure.domain.User;
import com.bonheure.repository.CommandeRepository;
import com.bonheure.utils.ApiMapper;

@Service
public class CommandeService {

	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	private ApiMapper apiMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	
	//postCommande
	public CommandeDTO saveCommande(CommandeDTO commandeDTO) {

		commandeDTO.setReference(UUID.randomUUID().toString());

		Commande commande = apiMapper.fromDTOToBean(commandeDTO);

		commandeRepository.save(commande);

		return commandeDTO;
	}
	
	//getAllCommandes
    public List<CommandeDTO> getAllCommande() {
		
		List<CommandeDTO> commandeDTOs=new ArrayList<CommandeDTO>();
		
		List<Commande> commandes =commandeRepository.findAll();
		
		
		for (Commande commande:commandes) {
			
			if (commande  == null)
				return null;
			
			CommandeDTO commandeDTO = apiMapper.fromBeanToDTO(commande);
			commandeDTOs.add(commandeDTO);
		}

		return commandeDTOs;
	}

	// updateCommandeByNature

	public CommandeDTO updateCommandeByRefrence(String reference, CommandeDTO commandeDTO) {

		Commande oldCommande = commandeRepository. findOneByReference(reference).orElse(null);

		if (oldCommande != null) {
			apiMapper.updateBeanFromDto(commandeDTO, oldCommande);
			commandeRepository.save(oldCommande);
		}
		return commandeDTO;
	}


	
	
}
