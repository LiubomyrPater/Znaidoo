package com.finalproject.demo.service;

import com.finalproject.demo.entity.User;

public interface UserService {


    User save(User user);

    void confirmRegistration(String token);

    void registerNewUser(User user);

}
