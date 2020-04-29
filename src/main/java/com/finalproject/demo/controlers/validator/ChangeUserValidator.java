package com.finalproject.demo.controlers.validator;

import com.finalproject.demo.config.ApplicationProperties;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChangeUserValidator implements Validator {

    private final UserRepository userRepository;
    private final ApplicationProperties applicationProperties;

    public ChangeUserValidator(UserRepository userRepository, ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "not.empty","Not empty space");

        if (user.getEmail().trim().length() == 0)
            errors.rejectValue("email","not.empty","Not empty space");
        else if(!user.getEmail().trim().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            errors.rejectValue("email", "pattern.email", "You are kidding?");
        else if (userRepository.existsByEmail(user.getEmail()))
            errors.rejectValue("email", "email.exist", "User with these phone number already exist!");


        if (!user.getPhoneNumber().matches("(\\+38|0)[0-9]{10}"))
            errors.rejectValue("phoneNumber","pattern.phoneNumber", "Phone number doesn't matches \"+001234567890\"");
        else if (userRepository.existsByPhoneNumber(user.getPhoneNumber()))
            errors.rejectValue("phoneNumber", "phoneNumber.exist", "User with these phone number already exist!");


        if (user.getCountry().matches("--- Select ---")){
            errors.rejectValue("country", "selectPlease", "Select please");
        }

        if (user.getLanguage().matches("--- Select ---")){
            errors.rejectValue("language", "selectPlease", "Select please");
        }
    }
}
