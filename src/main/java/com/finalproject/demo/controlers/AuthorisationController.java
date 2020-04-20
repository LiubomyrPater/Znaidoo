package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.UserValidator;

import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.UserService;
import com.finalproject.demo.service.event.RegisterUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorisationController {


    private final UserValidator userValidator;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    public AuthorisationController(UserValidator userValidator, UserService userService, ApplicationEventPublisher eventPublisher) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
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





    @GetMapping("/demoPage")
    public String getDemoPage() {
        return "demoPage";
    }

    @GetMapping("/forgottenPassword")
    public String getForgottenPassword() {
        return "forgottenPass";
    }

   /* @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        return "redirect:/login";
    }*/


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

}
