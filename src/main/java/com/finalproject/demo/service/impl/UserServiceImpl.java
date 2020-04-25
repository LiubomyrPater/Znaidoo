package com.finalproject.demo.service.impl;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.entity.VerificationToken;
import com.finalproject.demo.exceptions.UserAlreadyExistException;
import com.finalproject.demo.repository.RoleRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.repository.VerificationTokenRepository;
import com.finalproject.demo.service.UserService;
import com.finalproject.demo.utils.SecurityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final VerificationTokenRepository tokenRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

/*
    @Override
    public User save(User userView){
        userView.getRole().add(roleRepository.findByName("ROLE_USER"));
        userView.setPassword(bCryptPasswordEncoder.encode(userView.getPassword()));
        return userRepository.save(userView);
    }
*/


    @Override
    public void confirmRegistration(String token) {
        VerificationToken tokenPersisted = tokenRepository.findByToken(token);
        if (tokenPersisted != null && tokenIsValid(tokenPersisted)) {
            User user = tokenPersisted.getUser();
            user.setEnabled(true);
            userRepository.save(user);
        }

    }

    private boolean tokenIsValid(VerificationToken token) {
        return Duration.between(token.getExpiredDate(), Instant.now()).isNegative();
    }


    @Override
    public void registerNewUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())
                || userRepository.existsByPhoneNumber(user.getPhoneNumber())
                || userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException();
        }


        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.getRole().add(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public Long findUserCurrentId() {
        return userRepository.findByUsername(SecurityUtils.getCurrentUserName()).get().getId();
    }
}
