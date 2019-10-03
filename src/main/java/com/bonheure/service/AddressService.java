package com.bonheure.service;

import com.bonheure.controller.dto.AddressDTO;
import com.bonheure.domain.Address;
import com.bonheure.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address = getAddressFromDto(addressDTO);

        addressRepository.save(address);


        return addressDTO;

    }

    public AddressDTO getAddressByReference(String reference) {
        Address address = addressRepository.findByReference(reference);

        AddressDTO addressDTO = getAddressDTOFromAddress(address);

        return addressDTO;
    }


    private AddressDTO getAddressDTOFromAddress(Address address) {

        AddressDTO addressDTO = new AddressDTO();


        addressDTO.setPostalCode(address.getPostalCode());
        addressDTO.setReference(address.getReference());
        addressDTO.setRegion(address.getRegion());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setType(address.getType());


        return addressDTO;
    }


    private Address getAddressFromDto(AddressDTO addressDTO) {
        Address address = new Address();


        address.setPostalCode(addressDTO.getPostalCode());
        address.setReference(addressDTO.getReference());
        address.setRegion(addressDTO.getRegion());
        address.setStreet(addressDTO.getStreet());
        address.setType(addressDTO.getType());


        return address;


    }

}
