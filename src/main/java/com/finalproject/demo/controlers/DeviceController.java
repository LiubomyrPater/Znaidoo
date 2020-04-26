package com.finalproject.demo.controlers;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.Principal;
import java.util.Set;

@Controller
public class DeviceController {

    private final DeviceService deviceService;


    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;

    }




    @GetMapping("devices")
    public @ResponseBody Set<Device> setDevices (Principal principal, Model model){
        Set<Device> devices = deviceService.findDevicesByUser(principal);
        model.addAttribute("devices", devices);
        return devices;
    }


}
