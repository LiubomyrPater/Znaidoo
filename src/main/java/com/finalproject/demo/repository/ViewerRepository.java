package com.finalproject.demo.repository;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.Viewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long> {


    @Query("SELECT v.devices FROM Viewer v where v.id=?1")
    Set<Device> findViewersDevices(Long viewerId);

}
