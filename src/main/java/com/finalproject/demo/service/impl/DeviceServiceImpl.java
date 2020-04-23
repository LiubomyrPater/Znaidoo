package com.finalproject.demo.service.impl;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.service.DeviceService;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void connectToUser(Device device, Principal principal) {
        userRepository.findByUsername(principal.getName())
                .get()
                .getDevices()
                .add(deviceRepository.findDeviceBySerialNumber(device.getSerialNumber()));
    }

    //
//    @Override
//    public Optional<Device> findById(Integer id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Device update(Device device) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//
//    }

//    @Override
//    public Page<Device> findByUserId(Integer userId, Pageable pageable) {
//        return deviceRepository.findById(userId, pageable);
//    }

}
