package com.bonheure.service;

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.controller.dto.CategoryDTO;
import com.bonheure.domain.Address;
import com.bonheure.domain.Category;
import com.bonheure.repository.AddressRepository;
import com.bonheure.utils.ApiMapper;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private ApiMapper apiMapper;
    
    
    
    public AddressDTO saveAddress(AddressDTO addressDTO) {

    	addressDTO.setReference(UUID.randomUUID().toString());
    	Address address = apiMapper.fromDTOToBean(addressDTO);
        addressRepository.save(address);

        return addressDTO;

    }
    
    
    public AddressDTO getAddressByReference(String reference) {
    	Address address = addressRepository.findOneByReference(reference).
                orElse(null);

        if (address == null)
            return null;
        AddressDTO addressDTO = apiMapper.fromBeanToDTO(address);

        return addressDTO;
    }
    
    
    public void deleteAddressByReference(String reference) {
    	Address address = addressRepository.findOneByReference(reference).
                orElse(null);


    	addressRepository.delete(address);

    }
    
    public AddressDTO updateAddressByReference(String reference, AddressDTO addressDTO) {

        //TODO throw exception if not found
    	Address oldAddress= addressRepository.findOneByReference(reference).orElse(null);

        if (oldAddress != null) {
            apiMapper.updateBeanFromDto(addressDTO, oldAddress);
            addressRepository.save(oldAddress);
        }
        return addressDTO;
    }
}
