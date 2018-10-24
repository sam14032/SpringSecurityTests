package com.SpringProject.demo.WebController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class DefaultController {
    @GetMapping("/")
    public String home1()
{
    return "/home";
}
    @GetMapping("/home")
    public String home()
    {
        return "/home";
    }
    @GetMapping("/login")
    public String login()
    {
        return "/login";
    }
    @GetMapping("/userInfo")
    public String userInfo()
    {
        return "/userInfo";
    }
    @GetMapping("/register")
    public String register()
    {
        return "/register";
    }
}
