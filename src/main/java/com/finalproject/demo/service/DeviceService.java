package com.finalproject.demo.service;

import com.finalproject.demo.entity.Device;
import java.util.Optional;

public interface DeviceService {

    Device create(Device device);

    Optional<Device> findById(Integer id);

    Device update(Device device);

    void deleteById(Integer id);


}
