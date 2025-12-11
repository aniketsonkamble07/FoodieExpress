package com.foodieexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foodies")
public class AuthController {

    @GetMapping("/login")
    public String signupPage()
    {
        return "signup";
    }
}
