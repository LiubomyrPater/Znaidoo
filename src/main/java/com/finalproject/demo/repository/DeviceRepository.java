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






/*

    @Query("select d from Device d where d.userId = ?1")
    Optional<Set<Device>> findDevicesByUserId_____(Long userId);

    @Query("select ")



*/
//    Page<Device> findById(Integer id, Pageable pageable);




}
