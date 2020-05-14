package com.finalproject.demo.controlers.validator;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.service.dto.DeviceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class UserDeviceValidator implements Validator {

    private final DeviceRepository deviceRepository;

    public UserDeviceValidator(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return DeviceDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        DeviceDTO deviceDTO = (DeviceDTO) o;


        log.info("validator");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "not.empty","Not empty");


        if (deviceDTO.getSerialNumber().length() != 10) {
            errors.rejectValue("serialNumber", "device.serialNumber.size","Serial number size must be 10 numbers length!");
        }else {
            if (deviceRepository.findDeviceBySerialNumber(deviceDTO.getSerialNumber()).isPresent()){
                if (deviceRepository.findDeviceBySerialNumber(deviceDTO.getSerialNumber()).get().isUsingUser()) {
                    errors.rejectValue("serialNumber", "device.used", "Device already is using!");
                }
            }else {
                errors.rejectValue("serialNumber", "device.notExist", "Device not exist!");
            }
        }



    }
}
