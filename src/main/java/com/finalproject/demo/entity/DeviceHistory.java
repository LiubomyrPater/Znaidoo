package com.finalproject.demo.entity;

import com.finalproject.demo.entity.enumerations.DeviceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
public class DeviceHistory {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private DeviceStatus deviceStatus;

    private Integer batteryRate;

    private LocalDateTime localDateTime;

    private Float longitude;

    private Float latitude;
}
