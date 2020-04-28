package com.finalproject.demo.controlers.validator;

import com.finalproject.demo.entity.Device;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EditDeviceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Device.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Device device = (Device) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "not.empty","Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "periodLink", "not.empty","Not empty");

        if (device.getPeriodLink() < 5 || device.getPeriodLink() > 1000) {
            errors.rejectValue("periodLink", "periodLink.size", "Wrong period!");
        }

    }


}
