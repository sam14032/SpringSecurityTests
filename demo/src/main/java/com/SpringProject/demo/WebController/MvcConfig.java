package com.SpringProject.demo.WebController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{

    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/userInfo").setViewName("userInfo");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/login").setViewName("login");
    }
}
