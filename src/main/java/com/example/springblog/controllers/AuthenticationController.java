package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    private UserRepository userDao;

    public AuthenticationController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}
