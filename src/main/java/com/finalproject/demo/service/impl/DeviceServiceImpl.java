package com.finalproject.demo.service.impl;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.repository.ViewerRepository;
import com.finalproject.demo.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Set;


@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final ViewerRepository viewerRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, UserRepository userRepository, ViewerRepository viewerRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.viewerRepository = viewerRepository;
    }

    @Override
    public void create(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void connectToUser(Device device, Principal principal) {


        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("user with id " + principal.getName() + " was not found"));

        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(device.getSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException("user with id " + device.getSerialNumber() + " was not found"));

        persistedDevice.setName(device.getName());
        persistedDevice.setUsingUser(true);
        persistedDevice.getViewers().add(user.getViewer());

        user.getDevice().add(persistedDevice);

        deviceRepository.save(persistedDevice);

        userRepository.save(user);

    }

    @Override
    public Set<Device> findDevicesByUser(Principal principal) {
        return userRepository.findUsersDevices(userRepository.findByUsername(principal.getName()).get().getId());
    }

    @Override
    public Set<Device> findDevicesByViewer(Principal principal) {
        return viewerRepository.findViewersDevices(userRepository
                .findByUsername(principal.getName())
                .get()
                .getViewer()
                .getId()
        );
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
