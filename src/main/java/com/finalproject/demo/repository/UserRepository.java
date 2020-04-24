package com.finalproject.demo.repository;

import com.finalproject.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Integer> {



    Optional<User> findByUsername(String name);

    boolean existsByEmail(String email);
}
