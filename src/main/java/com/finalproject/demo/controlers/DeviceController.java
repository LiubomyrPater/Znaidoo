package com.finalproject.demo.controlers;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class DeviceController {

    private final DeviceService deviceService;


    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;

    }

    @GetMapping
    public @ResponseBody Set<Device> setDevices (){
        Set<Device> devices = new HashSet<>();
        return devices;
    }



    /*
    @RequestMapping(value = {"??????????"}, method = RequestMethod.GET)
    public String listDevices(ModelMap model, Principal principal){
        Set<Device> devices = deviceService.findDevicesByUser(principal).orElse(new HashSet<>());
        model.addAttribute("devices", devices);
        return "devices";
    }*/
}
