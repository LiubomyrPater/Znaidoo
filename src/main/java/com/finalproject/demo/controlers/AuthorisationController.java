package com.finalproject.demo.controlers;

import com.finalproject.demo.controlers.validator.UserValidator;
import com.finalproject.demo.entity.User;
import com.finalproject.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthorisationController {


    private final UserValidator userValidator;

    private final UserService userService;


    public AuthorisationController(UserValidator userValidator, UserService  userService) {
        this.userValidator = userValidator;
        this.userService = userService;
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

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(user);
        return "redirect:/login";
    }


}
