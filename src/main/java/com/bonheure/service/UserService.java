package com.bonheure.service;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.repository.UserRepository;
import com.bonheure.utils.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ApiMapper apiMapper;


    public UserDTO saveUser(UserDTO userDTO) {

        userDTO.setReference(UUID.randomUUID().toString());
        User user = apiMapper.fromDTOToBean(userDTO);
        userRepository.save(user);

        return userDTO;

    }

    public UserDTO getUserByReference(String reference) {
        User user = userRepository.findOneByReference(reference).
                orElse(null);

        if (user == null)
            return null;
        UserDTO userDTO = apiMapper.fromBeanToDTO(user);

        return userDTO;
    }


    public void deleteUserByReference(String reference) {
        User user = userRepository.findOneByReference(reference).
                orElse(null);


        userRepository.delete(user);

    }

    public UserDTO updateUserByReference(String reference, UserDTO userDTO) {

        //TODO throw exception if not found
        User oldUser = userRepository.findOneByReference(reference).orElse(null);

        if (oldUser != null) {
            apiMapper.updateBeanFromDto(userDTO, oldUser);
        }
        return userDTO;
    }


}
