package com.bonheure.service;


import com.bonheure.controller.dto.UserDTO;
import com.bonheure.domain.User;
import com.bonheure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;




    public UserDTO saveUser(UserDTO userDTO)
    {
        User user = getUserFromDto(userDTO);

        userRepository.save(user);

        return userDTO;

    }

    public UserDTO getUserByReference(String reference)
    {
        User user = userRepository.findByReference(reference);

        UserDTO userDTO =  getUserDTOFromUser(user);

        return userDTO;
    }

    private UserDTO getUserDTOFromUser(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setActivated(user.getActivated());
        userDTO.setEmail(user.getEmail());
        userDTO.setActivationDate(user.getActivationDate());
        userDTO.setCreationDate(user.getCreationDate());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getEmail());
        userDTO.setMobileNumber(user.getMobileNumber());
        userDTO.setModificationDate(user.getModificationDate());
        userDTO.setPassword(user.getPassword());
        userDTO.setReference(user.getReference());
        userDTO.setEmail(user.getEmail());


        return userDTO;    }

    private User getUserFromDto(UserDTO userDTO) {
        User user = new User();

        user.setActivated(userDTO.getActivated());
        user.setEmail(userDTO.getEmail());
        user.setActivationDate(userDTO.getActivationDate());
        user.setCreationDate(userDTO.getCreationDate());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getEmail());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setModificationDate(userDTO.getModificationDate());
        user.setPassword(userDTO.getPassword());
        user.setReference(userDTO.getReference());
        user.setEmail(userDTO.getEmail());


        return user;
    }


}
