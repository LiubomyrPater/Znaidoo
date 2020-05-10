package com.finalproject.demo.service.interfaces;

import com.finalproject.demo.entity.Device;

import java.security.Principal;
import java.util.Set;

public interface DeviceService {

    void create(Device device);

    void connectDeviceToUser(Device device, Principal principal);

    void addViewerToDevice(String username, String serialNumber);

    Set<Device> findDevicesByUser(Principal principal);

    Set<Device> findDevicesByViewer(Principal principal);

    void changeDevice(Device device);

    void deleteViewer(String SN, Long id);
}
