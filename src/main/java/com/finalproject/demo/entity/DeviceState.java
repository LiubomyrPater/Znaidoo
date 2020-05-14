package com.finalproject.demo.entity;

import com.finalproject.demo.entity.valueObjects.Status;
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
public class DeviceState {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Status status;

    private Integer batteryRate;

    private LocalDateTime localDateTime;

    private Float longitude;

    private Float latitude;
}
