package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.AdminDeviceValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.valueObjects.Type;
import com.finalproject.demo.repository.TypeRepository;
import com.finalproject.demo.service.dto.DeviceDTO;
import com.finalproject.demo.service.interfaces.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminController {

    private final AdminDeviceValidator adminDeviceValidator;
    private final DeviceService deviceService;
    private final TypeRepository typeRepository;

    public AdminController(AdminDeviceValidator adminDeviceValidator,
                           DeviceService deviceService,
                           TypeRepository typeRepository) {
        this.adminDeviceValidator = adminDeviceValidator;
        this.deviceService = deviceService;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/admin")
    public String adminMainPage() {
        return "admin";
    }

    @GetMapping("/admin/createNewDevice")
    public String createDevicePage(Model model) {
        model.addAttribute("deviceForm", new DeviceDTO());
        List<Type> types = typeRepository.findAllBy();
        List<String> typesName = new ArrayList<>();
        types.forEach(x-> typesName.add(x.getName()));
        model.addAttribute("types",typesName);

        return "createNewDevice";
    }


    @PostMapping("/admin/createNewDevice")
    public String registration(@ModelAttribute("deviceForm") DeviceDTO deviceDTO,
                               BindingResult bindingResult) {
        adminDeviceValidator.validate(deviceDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createNewDevice";
        }

        deviceService.create(deviceDTO);
        return "redirect:/";
    }
}
