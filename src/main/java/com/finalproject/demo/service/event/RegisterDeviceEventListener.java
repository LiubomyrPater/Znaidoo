package com.finalproject.demo.service.event;

import com.finalproject.demo.entity.Device;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RegisterDeviceEventListener {

    @EventListener
    public void handleRegistrationUser(RegisterDeviceEvent event) {
        Device device = event.getDevice();
        String baseUrl = event.getAppUrl();

    }
}
