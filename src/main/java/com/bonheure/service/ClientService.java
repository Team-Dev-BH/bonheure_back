package com.bonheure.service;

import com.bonheure.controller.dto.ClientDTO;
 
import com.bonheure.domain.Client;
import com.bonheure.domain.User;
import com.bonheure.repository.ClientRepository;
 
import com.bonheure.utils.ApiMapper;

 
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ClientService {


    @Autowired
    ClientRepository clientRepository;
     
    @Autowired
    private ApiMapper apiMapper;


    public ClientDTO saveClient(ClientDTO clientDTO) {

        clientDTO.setReference(UUID.randomUUID().toString());
        Client client = apiMapper.fromDTOToBean(clientDTO);
        clientRepository.save(client);

        return clientDTO;

    }

    public ClientDTO getClientByReference(String reference) {
        Client client = clientRepository.findOneByReference(reference).
                orElse(null);

        if (client == null)
            return null;
        ClientDTO clientDTO = apiMapper.fromBeanToDTO(client);

        return clientDTO;
    }

     public void deleteClientByReference(String reference) {
        Client client = clientRepository.findOneByReference(reference).
        		orElse(null);

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