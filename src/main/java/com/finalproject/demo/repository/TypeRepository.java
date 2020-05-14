package com.finalproject.demo.repository;


import com.finalproject.demo.entity.valueObjects.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

    List<Type>findAllBy();

    Type findByName(String name);
}
