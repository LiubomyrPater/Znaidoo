package com.finalproject.demo.service.mapper;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DeviceMapper {

    public Device toEntity(DeviceDTO deviceDTO) {

        Device result = new Device();
        result.setId(deviceDTO.getId());
        result.setType(deviceDTO.getType());
        result.setSerialNumber(deviceDTO.getSerialNumber());
        result.setName(deviceDTO.getName());
        result.setPeriodLink(deviceDTO.getPeriodLink());
        result.setBattery(deviceDTO.getBattery());
        result.setUsingUser(deviceDTO.isUsingUser());
        result.setUser(deviceDTO.getUser());
        result.setViewers(deviceDTO.getViewers());
        result.setDeviceState(deviceDTO.getDeviceState());

        return result;
    }


    public DeviceDTO toDTO(Device device) {

        DeviceDTO result = new DeviceDTO();

        result.setId(device.getId());
        result.setType(device.getType());
        result.setSerialNumber(device.getSerialNumber());
        result.setName(device.getName());
        result.setPeriodLink(device.getPeriodLink());
        result.setBattery(device.getBattery());
        result.setUsingUser(device.isUsingUser());
        result.setUser(device.getUser());
        result.setViewers(device.getViewers());
        result.setDeviceState(device.getDeviceState());

        return result;
    }

}
