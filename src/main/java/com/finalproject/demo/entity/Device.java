package com.finalproject.demo.entity;

import com.finalproject.demo.entity.valueObjects.Type;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor

@Entity
public class Device {

    @Id
    @GeneratedValue
    private Long id;

   /* @Enumerated(value = EnumType.STRING)
    private Type type;*/

    @ManyToOne
    private Type type;

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
    private Set<DeviceState> deviceState = new HashSet<>();


}
