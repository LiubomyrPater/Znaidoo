package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.AdminDeviceValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    private final AdminDeviceValidator adminDeviceValidator;
    private final DeviceService deviceService;


    public AdminController(AdminDeviceValidator adminDeviceValidator, DeviceService deviceService) {
        this.adminDeviceValidator = adminDeviceValidator;
        this.deviceService = deviceService;
    }

    @GetMapping("/admin")
    public String adminMainPage() {
        return "admin";
    }

    @GetMapping("/admin/createNewDevice")
    public String createDevicePage(Model model) {
        model.addAttribute("deviceForm", new Device());
        return "createNewDevice";
    }


    @PostMapping("/admin/createNewDevice")
    public String registration(@ModelAttribute("deviceForm") Device device, BindingResult bindingResult) {
        adminDeviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createNewDevice";
        }
        deviceService.create(device);
        return "createNewDevice";
    }
}
