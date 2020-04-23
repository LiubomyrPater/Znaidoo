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
public class AdminDeviceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Device.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Device device = (Device) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serialNumber", "required.serialNumber");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deviceType", "Not empty");
        if (device.getSerialNumber().length() < 10) {
            errors.rejectValue("serialNumber", "Size.deviceForm.serialNumber");
        }

    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        return messageSource;
    }
}


