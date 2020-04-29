package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.ChangeUserValidator;
import com.finalproject.demo.controlers.validator.UserDeviceValidator;
import com.finalproject.demo.controlers.validator.UserValidator;
import com.finalproject.demo.entity.Device;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.service.interfaces.DeviceService;
import com.finalproject.demo.service.interfaces.UserService;
import com.finalproject.demo.service.event.RegisterUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;


@Controller
@Slf4j
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final UserDeviceValidator userDeviceValidator;
    private final DeviceService deviceService;
    private final UserRepository userRepository;
    private final ChangeUserValidator changeUserValidator;

    public UserController(UserValidator userValidator, UserService userService,
                          ApplicationEventPublisher eventPublisher,
                          UserDeviceValidator userDeviceValidator, DeviceService deviceService, UserRepository userRepository, ChangeUserValidator changeUserValidator) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.userDeviceValidator = userDeviceValidator;
        this.deviceService = deviceService;
        this.userRepository = userRepository;
        this.changeUserValidator = changeUserValidator;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @GetMapping("/confirmRegistration")
    public String confirmRegistration(@RequestParam String token) {
        userService.confirmRegistration(token);
        return "redirect:/login";
    }

    @GetMapping("/forgottenPassword")
    public String getForgottenPassword() {
        return "forgottenPass";
    }












    @GetMapping("/account")
    public String getAccountPage(@RequestParam("username") String username,
                                 @ModelAttribute("changeForm") User User,
                                 Principal principal, Model model) {
        if (!principal.getName().equals(username))
            return "errorPage";
        User persistedUser = userRepository.findByUsername(username).get();
        model.addAttribute("changeForm", persistedUser);
        return "account";
    }


    @PostMapping("/account")
    public String postAccountPage(@ModelAttribute("changeForm") User user,
                                  BindingResult bindingResult) {
        changeUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "account";
        userService.changeUser(user);
        return "redirect:home";
    }


    @GetMapping("/help")
    public String getHelpPage() {
        return "help";
    }

    @GetMapping("/demoPage")
    public String getDemoPage() {
        return "demoPage";
    }

    @GetMapping({"/home", "/"})
    public String home(Model model, Principal principal) {
        model.addAttribute("message", "Hello from controller");
        Set<Device> viewerDevices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("username",principal.getName());
        model.addAttribute("devices", viewerDevices);
        return "home";
    }

    @GetMapping("/userAddDevice")
    public String getUserAddDevicePage(Model model) {
        model.addAttribute("userAddDeviceForm", new Device());
        return "userAddDevice";
    }


    @PostMapping("/registration")
    public String registration( @ModelAttribute("userForm") User user,
                                HttpServletRequest request, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.registerNewUser(user);
        eventPublisher.publishEvent(new RegisterUserEvent(this, user, request.getContextPath()));
        return "success";
    }


    @PostMapping("/userAddDevice")
    public String registration(@ModelAttribute("userAddDeviceForm") Device device,
                               BindingResult bindingResult, Principal principal, Model model) {

        userDeviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors())
            return "userAddDevice";

        deviceService.connectDeviceToUser(device, principal);

        Set<Device> viewerDevices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", viewerDevices);
        return "redirect:home";
    }
}