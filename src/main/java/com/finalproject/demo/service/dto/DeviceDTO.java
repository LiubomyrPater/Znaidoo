package com.finalproject.demo.service.dto;

import com.finalproject.demo.entity.DeviceState;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.entity.Viewer;
import com.finalproject.demo.entity.valueObjects.Status;
import com.finalproject.demo.entity.valueObjects.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode
public class DeviceDTO {


    private String type;

    private String serialNumber;

    private String name;

    private Integer periodLink;

    private Integer battery;

    private boolean usingUser;

    private User user;

    private Set<Viewer> viewers = new HashSet<>();

    private List<DeviceState> deviceState = new ArrayList<>();
}
