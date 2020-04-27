package com.finalproject.demo.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Viewer {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToMany(mappedBy = "viewers", fetch = FetchType.EAGER)

    private Set<Device> devices= new HashSet<>();



}
