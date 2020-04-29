package com.finalproject.demo.controlers.validator;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class SimpleUserValidator implements Validator {

    private final UserRepository userRepository;

    public SimpleUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        /*log.info(user.getUsername()+ "validator");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "not.empty","Not empty");

        if (!userRepository.existsByUsername(user.getUsername()))
            errors.rejectValue("username", "user.notExist", "User not exist!");*/
    }
}
