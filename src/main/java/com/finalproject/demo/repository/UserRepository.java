package com.finalproject.demo.repository;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByUsername(String name);

    boolean existsByUsername(String userName);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
/*
    @Query("select u.devices from User u where u.id=?1")
    Set<Device> findUsersDevices(String userId);*/

}
