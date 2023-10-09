package org.softuni.web;

import org.softuni.model.dto.UserLoginDto;
import org.softuni.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;

    private UserLoginController(UserService userService){

        this.userService = userService;

    }

    @GetMapping("/users/login")
    public String login() {


        return "auth-login";
    }

    @GetMapping("/users/logout")
    public String logout(){

        userService.logoutUser();

        return "index";
    }

    @PostMapping("/users/login")
    public String login(UserLoginDto userLoginDto){
        boolean loginSuccessful = userService.loginUser(userLoginDto);

        return loginSuccessful ? "index" : "auth-login";

    }
}

