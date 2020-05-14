package com.finalproject.demo.service.interfaces;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.dto.UserDTO;


public interface UserService {


    void confirmRegistration(String token);

    User registerNewUser(UserDTO userDTO);

    Long findUserCurrentId();

    void changeUser(UserDTO userDTO);

}
