package com.SpringProject.demo;


import com.SpringProject.demo.Security.CustomAuthentification;
import com.SpringProject.demo.Security.CustomUserDetailsService;
import com.SpringProject.demo.Security.WebSecurityConfig;
import com.SpringProject.demo.WebController.RegistrationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private DatabaseCommand databaseCommand;


    @Bean
    public CommandLineRunner demo(UserInfoRepository userInfoRepository) {
        return (args) -> {

            databaseCommand.registerUser(new UserInfo("us", "pw"), userInfoRepository);
            databaseCommand.showAllUserEntries(userInfoRepository);
        };
    }
}
