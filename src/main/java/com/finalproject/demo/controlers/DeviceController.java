package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.EditDeviceValidator;
import com.finalproject.demo.controlers.validator.SimpleUserValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@Controller
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;
    private final SimpleUserValidator simpleUserValidator;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final EditDeviceValidator editDeviceValidator;



    public DeviceController(DeviceService deviceService, SimpleUserValidator simpleUserValidator, DeviceRepository deviceRepository, UserRepository userRepository, EditDeviceValidator editDeviceValidator) {
        this.deviceService = deviceService;
        this.simpleUserValidator = simpleUserValidator;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.editDeviceValidator = editDeviceValidator;
    }

    @GetMapping("/editDevice")
    public String getEditDevicePage(@RequestParam("deviceSN") String sn,
                                    @ModelAttribute("editDeviceForm") Device device,
                                    Principal principal, Model model) {

        Set<Device> usersDevices = userRepository.findByUsername(principal.getName()).get().getDevice();
        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(sn).get();
        if (!usersDevices.contains(persistedDevice)){
            return "errorPage";
        }
            model.addAttribute("persistedDevice", persistedDevice);
        return "editDevice";
    }



    @PostMapping("/editDevice")
    public String editDevice(@ModelAttribute("editDeviceForm") Device device, BindingResult bindingResult) {

        editDeviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return "editDevice";
        }
        deviceService.changeDevice(device);
        return "redirect:home";
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

        if (bindingResult.hasErrors())
            return "setViewer";

        deviceService.addViewerToDevice(user.getUsername(), "4444444444");

        Set<Device> devices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", devices);

        return "redirect:home";
    }

}
