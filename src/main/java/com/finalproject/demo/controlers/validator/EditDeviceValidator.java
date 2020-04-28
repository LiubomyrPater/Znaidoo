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
/*
        Device device = (Device) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serialNumber", "not.empty", "Not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceType", "not.empty","Not empty");
        if (device.getSerialNumber().length() != 10) {
            errors.rejectValue("serialNumber", "device.serialNumber.size", "Serial number size must be 10 numbers length!");
        }*/

    }


}
