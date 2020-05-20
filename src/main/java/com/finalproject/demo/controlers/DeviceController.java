package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.EditDeviceValidator;
import com.finalproject.demo.controlers.validator.SimpleUserValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.DeviceRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.service.dto.DeviceDTO;
import com.finalproject.demo.service.dto.UserDTO;
import com.finalproject.demo.service.interfaces.DeviceService;
import com.finalproject.demo.service.mapper.DeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
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
    private final DeviceMapper deviceMapper;


    public DeviceController(DeviceService deviceService,
                            SimpleUserValidator simpleUserValidator,
                            DeviceRepository deviceRepository,
                            UserRepository userRepository,
                            EditDeviceValidator editDeviceValidator,
                            DeviceMapper deviceMapper){
        this.deviceService = deviceService;
        this.simpleUserValidator = simpleUserValidator;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
        this.editDeviceValidator = editDeviceValidator;
        this.deviceMapper = deviceMapper;
    }


    @GetMapping("/editDevice")
    public String getEditDevicePage(@RequestParam("deviceSN") String sn,
                                    @ModelAttribute("editDeviceForm") DeviceDTO deviceDTO,
                                    Principal principal,
                                    Model model){
        Set<Device> usersDevices = userRepository.findByUsername(principal.getName()).get().getDevice();
        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(sn).get();
        if (!usersDevices.contains(persistedDevice))
            return "errorPage";
        DeviceDTO persistedDeviceDTO = deviceMapper.toDTO(persistedDevice);
        model.addAttribute("editDeviceForm", persistedDeviceDTO);
        persistedDevice.getViewers().remove(userRepository.findByUsername(principal.getName()).get().getViewer());
        model.addAttribute("viewers", persistedDeviceDTO);
        return "editDevice";
    }

    @PostMapping("/editDevice")
    public String editDevice(@ModelAttribute("editDeviceForm") DeviceDTO deviceDTO,
                             BindingResult bindingResult)
    {
        editDeviceValidator.validate(deviceDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "editDevice";
        deviceService.changeDevice(deviceDTO);
        return "redirect:home";
    }


    @GetMapping("/setViewer")
    public String getUserAddDevicePage(@RequestParam("deviceSN") String sn,
                                       Principal principal,
                                       Model model)
    {
        log.info("get deviceSN " + sn);
        Set<Device> usersDevices = userRepository.findByUsername(principal.getName()).get().getDevice();
        Device persistedDevice = deviceRepository.findDeviceBySerialNumber(sn).get();
        if (!usersDevices.contains(persistedDevice))
            return "errorPage";
        model.addAttribute("addViewerForm", new UserDTO());
        return "setViewer";
    }




    @PostMapping("/setViewer")
    public String setViewerFormPost(@ModelAttribute("addViewerForm") UserDTO userDTO,
                                    @ModelAttribute("deviceSN") String sn,
                                    BindingResult bindingResult,
                                    Principal principal,
                                    Model model)
    {
        log.info("post deviceSN " + sn);
        log.info(userDTO.getUsername());

        if (!userRepository.findByUsername(userDTO.getUsername()).isPresent())
            return "setViewer";

        /**Не розумію чому не працює simpleUserValidator
        simpleUserValidator.validate(user,bindingResult);
        if (bindingResult.hasErrors())
            return "setViewer";*/

        deviceService.addViewerToDevice(userDTO.getUsername(), sn);
        Set<DeviceDTO> devices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", devices);
        return "redirect:home";
    }


    @GetMapping("/editDevice/delete")
    public String deleteViewer(@RequestParam("deviceSN") String deviceSN,
                               @RequestParam("id") Long id,
                               Principal principal){

        if (userRepository.findByUsername(principal.getName()).get()
                .getDevice()
                .contains(deviceRepository.findDeviceBySerialNumber(deviceSN).get())){
            deviceService.deleteViewer(deviceSN, id);
            return "redirect:/editDevice?deviceSN=" + deviceSN;
        }else
            return "errorPage";
    }
}
