package com.finalproject.demo.service.interfaces;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.Viewer;
import com.finalproject.demo.entity.enumerations.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.Query;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

public interface DeviceService {

    void create(Device device);

    void connectDeviceToUser(Device device, Principal principal);

    void addViewerToDevice(String username, String serialNumber);

    Set<Device> findDevicesByUser(Principal principal);

    Set<Device> findDevicesByViewer(Principal principal);

    void changeDevice(Device device);
}
