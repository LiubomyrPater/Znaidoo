package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.ChangeUserValidator;
import com.finalproject.demo.controlers.validator.UserDeviceValidator;
import com.finalproject.demo.controlers.validator.UserValidator;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.repository.RoleRepository;
import com.finalproject.demo.repository.UserRepository;
import com.finalproject.demo.service.dto.DeviceDTO;
import com.finalproject.demo.service.dto.UserDTO;
import com.finalproject.demo.service.interfaces.DeviceService;
import com.finalproject.demo.service.interfaces.UserService;
import com.finalproject.demo.service.event.RegisterUserEvent;
import com.finalproject.demo.service.mapper.UserMapper;
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
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserController(UserValidator userValidator, UserService userService,
                          ApplicationEventPublisher eventPublisher,
                          UserDeviceValidator userDeviceValidator,
                          DeviceService deviceService,
                          UserRepository userRepository,
                          ChangeUserValidator changeUserValidator,
                          RoleRepository roleRepository,
                          UserMapper userMapper) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.userDeviceValidator = userDeviceValidator;
        this.deviceService = deviceService;
        this.userRepository = userRepository;
        this.changeUserValidator = changeUserValidator;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String basePage(Principal principal)
    {
        if(principal != null){
            if (userRepository.findByUsername(principal.getName())
                    .get()
                    .getRole()
                    .contains(roleRepository.findByName("ROLE_ADMIN"))
                    )
                return "redirect:admin";
            else
                return "redirect:home";
        }
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage(Principal principal) {
        if(principal != null)
            return "redirect:/home";
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @GetMapping("/confirmRegistration")
    public String confirmRegistration(@RequestParam("token") String token) {
        userService.confirmRegistration(token);
        return "redirect:/login";
    }

    @GetMapping("/forgottenPassword")
    public String getForgottenPassword() {
        return "forgottenPass";
    }


    @GetMapping("/account")
    public String getAccountPage(@RequestParam("username") String username,
                                 @ModelAttribute("changeForm") UserDTO userDTO,
                                 Principal principal,
                                 Model model){
        if (!principal.getName().equals(username))
            return "errorPage";
        UserDTO persistedUserDTO = userMapper.toDTO(userRepository.findByUsername(username).get());
        model.addAttribute("changeForm", persistedUserDTO);
        return "account";
    }


    @PostMapping("/account")
    public String postAccountPage(@ModelAttribute("changeForm") UserDTO userDTO,
                                  BindingResult bindingResult) {
        changeUserValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "account";
        userService.changeUser(userDTO);
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


    @GetMapping("/default")
    public String getDefaultPage(Principal principal) {
        if (userRepository.findByUsername(principal.getName())
                .get()
                .getRole()
                .contains(roleRepository.findByName("ROLE_ADMIN"))
                )
            return "redirect:admin";
        else
            return "redirect:home";
    }



    @GetMapping("/home")
    public String getHomePage(Model model,
                              Principal principal){
        model.addAttribute("message", "Hello from controller");
        Set<DeviceDTO> viewerDevices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", viewerDevices);
        model.addAttribute("username",principal.getName());
        return "home";
    }

    @GetMapping("/userAddDevice")
    public String getUserAddDevicePage(Model model) {
        model.addAttribute("userAddDeviceForm", new DeviceDTO());
        return "userAddDevice";
    }


    @PostMapping("/registration")
    public String registration( @ModelAttribute("userForm") UserDTO userDTO,
                                HttpServletRequest request,
                                BindingResult bindingResult)
    {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User user = userService.registerNewUser(userDTO);
        eventPublisher.publishEvent(new RegisterUserEvent(this, user, request.getContextPath()));
        return "success";
    }


    @PostMapping("/userAddDevice")
    public String registration(@ModelAttribute("userAddDeviceForm") DeviceDTO deviceDTO,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model) {
        userDeviceValidator.validate(deviceDTO, bindingResult);
        if (bindingResult.hasErrors())
            return "userAddDevice";

        deviceService.connectDeviceToUser(deviceDTO, principal);

        Set<DeviceDTO> viewerDevices = deviceService.findDevicesByViewer(principal);
        model.addAttribute("devices", viewerDevices);
        return "redirect:home";
    }
}