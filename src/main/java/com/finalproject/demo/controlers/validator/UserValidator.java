package com.finalproject.demo.controlers.validator;

import com.finalproject.demo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "Not empty");
        if (user.getPassword().length() < 3) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "language", "Not empty");

    }
}
