package com.finalproject.demo.repository;

import com.finalproject.demo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Optional<Device> findDeviceBySerialNumber (String serialNumber);


    //@Query("SELECT d.viewers FROM Device d where d.id=?1")
    @Query("select d from Device d join d.viewers v where v.id=?1")
    Set<Device> findDeviceByViewers(Long viewerId);

}
