package org.softuni.web;

import org.softuni.model.dto.UserRegistrationDto;
import org.softuni.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;



    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(UserRegistrationDto userRegistrationDto) {

        userService.register(userRegistrationDto);
        return "redirect:/users/login";
    }
}
