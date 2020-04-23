package com.finalproject.demo.service.event;

import com.finalproject.demo.entity.Device;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class RegisterDeviceEvent extends ApplicationEvent {

    private Device device;

    private String appUrl;

    public RegisterDeviceEvent(Object source, Device device, String appUrl) {
        super(source);
        this.device = device;
        this.appUrl = appUrl;
    }
}
