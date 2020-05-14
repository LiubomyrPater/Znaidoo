package com.finalproject.demo.service.mapper;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(UserDTO userDTO) {

        User result = new User();

        result.setUsername(userDTO.getUsername());
        result.setPassword(userDTO.getPassword());
        result.setEmail(userDTO.getEmail());
        result.setPhoneNumber(userDTO.getPhoneNumber());
        result.setCountry(userDTO.getCountry());
        result.setLanguage(userDTO.getLanguage());
        result.setViewer(userDTO.getViewer());
        result.setDevice(userDTO.getDevice());
        result.setRole(userDTO.getRole());

        return result;
    }


    public UserDTO toDTO(User user) {

        UserDTO result = new UserDTO();

        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setEmail(user.getEmail());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setCountry(user.getCountry());
        result.setLanguage(user.getLanguage());
        result.setViewer(user.getViewer());
        result.setDevice(user.getDevice());
        result.setRole(user.getRole());

        return result;
    }

}
