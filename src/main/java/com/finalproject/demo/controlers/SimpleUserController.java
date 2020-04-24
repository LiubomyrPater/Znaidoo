package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.UserDeviceValidator;
import com.finalproject.demo.controlers.validator.UserValidator;

import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.DeviceService;
import com.finalproject.demo.service.UserService;
import com.finalproject.demo.service.event.RegisterUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
@Slf4j
public class SimpleUserController {


    private final UserValidator userValidator;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final UserDeviceValidator userDeviceValidator;
    private final DeviceService deviceService;


    public SimpleUserController(UserValidator userValidator, UserService userService, ApplicationEventPublisher eventPublisher, UserDeviceValidator userDeviceValidator, DeviceService deviceService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.userDeviceValidator = userDeviceValidator;
        this.deviceService = deviceService;
    }

    @GetMapping({"/home", "/"})
    public String home(Model model) {
        model.addAttribute("message", "Hello from controller");
        return "home";
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/home/userAddDevice")
    public String getUserAddDevicePage(Model model) {
        model.addAttribute("userAddDeviceForm", new Device());
        return "userAddDevice";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @GetMapping("/account")
    public String getAccountPage() {
        return "account";
    }

    @GetMapping("/demoPage")
    public String getDemoPage() {
        return "demoPage";
    }

    @GetMapping("/forgottenPassword")
    public String getForgottenPassword() {
        return "forgottenPass";
    }




    @PostMapping("/registration")
    public String registration(HttpServletRequest request, @ModelAttribute("userForm") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.registerNewUser(user);
        eventPublisher.publishEvent(new RegisterUserEvent(this, user, request.getContextPath()));
        return "success";
    }




    @GetMapping("/confirmRegistration")
    public String confirmRegistration(@RequestParam String token) {
        userService.confirmRegistration(token);
        return "redirect:/login";
    }




    @PostMapping("/home/userAddDevice")
    public String registration(@ModelAttribute("userAddDeviceForm") Device device, BindingResult bindingResult, Principal principal) {

        log.info("controller");

        userDeviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return "userAddDevice";
        }
        deviceService.connectToUser(device, principal);

        return "home";
    }

}