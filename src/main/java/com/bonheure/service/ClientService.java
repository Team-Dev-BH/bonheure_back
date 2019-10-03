package com.bonheure.service;

import com.bonheure.controller.dto.ClientDTO;
import com.bonheure.domain.Client;
import com.bonheure.repository.ClientRepository;
import com.bonheure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ClientService {


    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UserRepository userRepository;


    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = getClientFromDto(clientDTO);

        clientRepository.save(client);


        return clientDTO;

    }

    public ClientDTO getClientByReference(String reference) {
        Client client = clientRepository.findByReference(reference);

        ClientDTO clientDTO = getClientDTOFromClient(client);

        return clientDTO;
    }

    public void deleteClientByReference(String reference) {
        Client client = clientRepository.findByReference(reference);

        clientRepository.delete(client);

    }

    public ClientDTO updateClientByReference(String reference, ClientDTO clientDTO) {
        Client clientOld = clientRepository.findByReference(reference);
        Client clientNew = getClientFromDto(clientDTO);

        if (clientOld != null) {
            Update(clientOld, clientNew);

        }
        return clientDTO;
    }


    private void Update(Client ClientOld, Client ClientNew) {
        ClientOld.setActivated(ClientNew.getActivated());
        ClientOld.setActivationDate(ClientNew.getActivationDate());
        ClientOld.setCreationDate(ClientNew.getCreationDate());
        ClientOld.setEmail(ClientNew.getEmail());
        ClientOld.setFirstName(ClientNew.getFirstName());
        ClientOld.setLastName(ClientNew.getLastName());
        ClientOld.setMobileNumber(ClientNew.getMobileNumber());
        ClientOld.setModificationDate(ClientNew.getModificationDate());
        ClientOld.setPassword(ClientNew.getPassword());
        ClientOld.setReference(ClientNew.getReference());


        ClientOld.setBirthDate(ClientNew.getBirthDate());
        ClientOld.setPosition(ClientNew.getPosition());

        clientRepository.save(ClientOld);
    }

    private ClientDTO getClientDTOFromClient(Client client) {

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setBirthDate(client.getBirthDate());
        clientDTO.setPosition(client.getPosition());

        clientDTO.setActivated(client.getActivated());
        clientDTO.setActivationDate(client.getActivationDate());
        clientDTO.setCreationDate(client.getCreationDate());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setMobileNumber(client.getMobileNumber());
        clientDTO.setModificationDate(client.getModificationDate());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setReference(client.getReference());


        return clientDTO;
    }


    private Client getClientFromDto(ClientDTO clientDTO) {
        Client client = new Client();


        client.setBirthDate(clientDTO.getBirthDate());
        client.setPosition(clientDTO.getPosition());

        client.setActivated(clientDTO.getActivated());
        client.setActivationDate(clientDTO.getActivationDate());
        client.setCreationDate(clientDTO.getCreationDate());
        client.setEmail(clientDTO.getEmail());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setLastName(clientDTO.getLastName());
        client.setMobileNumber(clientDTO.getMobileNumber());
        client.setModificationDate(clientDTO.getModificationDate());
        client.setPassword(clientDTO.getPassword());
        client.setReference(clientDTO.getReference());


        return client;


    }
}
