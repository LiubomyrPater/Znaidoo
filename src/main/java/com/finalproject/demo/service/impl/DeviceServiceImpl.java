package com.finalproject.demo.service.impl;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.repository.ViewerRepository;
import com.finalproject.demo.service.interfaces.DeviceService;
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
    public void connectDeviceToUser(Device device, Principal principal) {

        User persistedUser = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("user with name " + principal.getName() + " was not found"));


        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(device.getSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException("device with id " + device.getSerialNumber() + " was not found"));


        persistedDevice.setName(device.getName());
        persistedDevice.setUsingUser(true);
        persistedDevice.getViewers().add(persistedUser.getViewer());
        persistedUser.getDevice().add(persistedDevice);
        userRepository.save(persistedUser);

        //зайве
        //deviceRepository.save(persistedDevice);
    }

    @Override
    public Set<Device> findDevicesByUser(Principal principal) {
        return userRepository.findUsersDevices(userRepository
                .findByUsername(principal.getName())
                .get()
                .getId()
        );
    }

    @Override
    public Set<Device> findDevicesByViewer(Principal principal) {

        Long viewerId = userRepository
                .findByUsername(principal.getName())
                .get()
                .getViewer()
                .getId();
        Set<Device> deviceByViewers = deviceRepository.findDeviceByViewers(viewerId
        );

        return deviceByViewers;


        /*
        return viewerRepository.findViewersDevices(userRepository
                .findByUsername(principal.getName())
                .get()
                .getViewer()
                .getId()
        );*/
    }


    @Override
    public void changeDevice(Device device) {
        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(device.getSerialNumber())
                .orElseThrow(() -> new EntityNotFoundException("device with id " + device.getSerialNumber() + " was not found"));

        persistedDevice.setName(device.getName());
        persistedDevice.setPeriodLink(device.getPeriodLink());
        deviceRepository.save(persistedDevice);

    }

    @Override
    public void addViewerToDevice(String username, String serialNumber) {

        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(serialNumber)
                .orElseThrow(() -> new EntityNotFoundException("device with id " + serialNumber + " was not found"));


        User persistedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user with username " + username + " was not found"));


        persistedDevice.getViewers().add(persistedUser.getViewer());
        deviceRepository.save(persistedDevice);

/**
        //не зберігає в базу
        Viewer persistedViewer = viewerRepository.findById(persistedUser.getViewer().getId()).get();
        persistedViewer.getDevices().add(persistedDevice);
        viewerRepository.save(persistedViewer);
*/
    }
}
