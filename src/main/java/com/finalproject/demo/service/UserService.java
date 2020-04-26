package com.finalproject.demo.service;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.entity.Viewer;

public interface UserService {


    void confirmRegistration(String token);

    void registerNewUser(User user);

    Long findUserCurrentId();

}
