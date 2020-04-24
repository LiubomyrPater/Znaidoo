package com.finalproject.demo.service;

import com.finalproject.demo.entity.User;

public interface UserService {


    /*User save(User userView);*/

    void confirmRegistration(String token);

    void registerNewUser(User user);

    Long findUserCurrentId();

}
