package com.finalproject.demo.repository;

import com.finalproject.demo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {



    Optional<Device> findDeviceBySerialNumber (String serialNumber);

//    List<Device> findDevice(Integer userId);
//
//    Page<Device> findById(Integer id, Pageable pageable);




}
