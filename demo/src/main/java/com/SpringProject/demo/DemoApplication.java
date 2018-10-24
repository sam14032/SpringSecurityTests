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
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private DatabaseCommand databaseCommand;


    @Bean
    public CommandLineRunner demo(UserInfoRepository userInfoRepository) {
        return (args) -> {
            RegistrationController.config(userInfoRepository);
            CustomAuthentification.config(userInfoRepository);
            WebSecurityConfig.Init(userInfoRepository);
            CustomUserDetailsService.Init(userInfoRepository);

            databaseCommand.registerUser(new UserInfo("us", "pw"), userInfoRepository);
            databaseCommand.showAllUserEntries(userInfoRepository);
        };
    }






    /*@RequestMapping(value="/userInfo",method = RequestMethod.GET)
    public @ResponseBody String getUserInfoByUsername() {
        String userlist = "";
        for(UserInfo user : userInfoRepository.findAll())
        {
            userlist += user.toString();
        }
        return userlist;
    }*/
}
