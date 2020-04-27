package com.finalproject.demo.repository;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.Viewer;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ViewerRepository extends JpaRepository<Viewer, Long> {


    Optional<Viewer>findById(Long id);

    @Query("SELECT v.devices FROM Viewer v where v.id=?1")
    //@Query("SELECT dv.device_id FROM device_viewers dv where dv.viewers_id=?1")
    Set<Device> findViewersDevices(Long viewerId);

}
