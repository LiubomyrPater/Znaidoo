package com.finalproject.demo.service.interfaces;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.service.dto.DeviceDTO;

import java.security.Principal;
import java.util.Set;

public interface DeviceService {

    void create(DeviceDTO deviceDTO);

    void connectDeviceToUser(DeviceDTO deviceDTO, Principal principal);

    void addViewerToDevice(String username, String serialNumber);

    Set<Device> findDevicesByUser(Principal principal);

    Set<DeviceDTO> findDevicesByViewer(Principal principal);

    void changeDevice(DeviceDTO deviceDTO);

    void deleteViewer(String SN, Long id);
}
