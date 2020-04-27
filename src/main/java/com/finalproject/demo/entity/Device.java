package com.finalproject.demo.entity;

import com.finalproject.demo.config.ApplicationProperties;
import com.finalproject.demo.entity.enumerations.DeviceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    //@Column(columnDefinition = "integer default 100")
    private Integer periodLink = 100;

    private Integer battery = 100;

    private boolean usingUser;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Viewer> viewers = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "device_id")
    private Set<DeviceHistory> deviceHistory = new HashSet<>();


}
