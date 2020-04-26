package com.finalproject.demo.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Viewer {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<Device> devices= new HashSet<>();



}
