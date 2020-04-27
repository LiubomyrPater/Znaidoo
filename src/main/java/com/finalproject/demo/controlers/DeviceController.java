package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.SimpleUserValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.Principal;
import java.util.Set;

@Controller
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;
    private final SimpleUserValidator simpleUserValidator;


    public DeviceController(DeviceService deviceService, SimpleUserValidator simpleUserValidator) {
        this.deviceService = deviceService;

        this.simpleUserValidator = simpleUserValidator;
    }




    @GetMapping("/setViewer")
    public String getUserAddDevicePage(Model model) {
        model.addAttribute("addViewerForm", new User());
        return "setViewer";
    }



    @PostMapping("/setViewer")
    public String registration(@ModelAttribute("addViewerForm") User user,
                               BindingResult bindingResult, Principal principal, Model model) {


        simpleUserValidator.validate(user,bindingResult);

        if (bindingResult.hasErrors()) {
            return "setViewer";
        }


        log.info(user.getUsername());



        deviceService.addNewViewer(user.getUsername(), "3333333333");

        /*Set<Device> devices = deviceService.findDevicesByUser(principal);
        model.addAttribute("devices", devices);*/

        Set<Device> devices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", devices);

        return "home";
    }








    //не використовується
    @GetMapping("devices")
    public @ResponseBody Set<Device> setDevices (Principal principal, Model model){
        Set<Device> devices = deviceService.findDevicesByUser(principal);
        model.addAttribute("devices", devices);
        return devices;
    }


}
