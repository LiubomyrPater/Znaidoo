package com.finalproject.demo.service.dto;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.Role;
import com.finalproject.demo.entity.Viewer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String passwordConfirm;

    private String email;

    private String phoneNumber;

    private String country;

    private String language;

    private boolean enabled;

    private Viewer viewer;

    private Set<Device> device = new HashSet<>();

    private Set<Role> role = new HashSet<>();

}
