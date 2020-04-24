package com.finalproject.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Viewer {

    @Id
    @GeneratedValue
    private Long id;


}
