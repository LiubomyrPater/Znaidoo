package com.finalproject.demo.entity;

import com.finalproject.demo.entity.enumerations.DeviceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter

@NoArgsConstructor

@Entity
public class Device {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private DeviceType deviceType;

    @Column(unique = true)
    private String serialNumber;

    private String name;

    private Integer periodLink;

    private boolean usingUser;

    @OneToMany
    @JoinColumn(name = "device_id")
    private Set<Viewer> viewers = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "device_id")
    private Set<DeviceHistory> deviceHistory = new HashSet<>();


}
