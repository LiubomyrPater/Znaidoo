package com.finalproject.demo.repository;

import com.finalproject.demo.entity.valueObjects.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
