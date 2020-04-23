package com.finalproject.demo.service;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.enumerations.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.Optional;

public interface DeviceService {

    void create(Device device);

    void connectToUser(Device device, Principal principal);

//    Optional<Device> findById(Integer id);
//
//    Device update(Device device);
//
//    void deleteById(Integer id);

//    Page<Device> findByUserId(Integer userId, Pageable pageable);

}
