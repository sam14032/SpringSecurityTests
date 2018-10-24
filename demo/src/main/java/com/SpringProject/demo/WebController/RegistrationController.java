package com.SpringProject.demo.WebController;

import com.SpringProject.demo.DatabaseCommand;
import com.SpringProject.demo.UserInfo;
import com.SpringProject.demo.UserInfoRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class RegistrationController {

    static UserInfoRepository userInfoRepository;

    public static void config(UserInfoRepository userInfoRepository){
        RegistrationController.userInfoRepository = userInfoRepository;}

    @RequestMapping(value="/register",consumes = "application/json",method=RequestMethod.POST)
    public String saveUserInfo(@RequestBody UserInfo userInfo)
    {
        DatabaseCommand databaseCommand = new DatabaseCommand();
        if (databaseCommand.getUserByUsername(userInfo.getUsername(),userInfoRepository) ==null)
        {
            databaseCommand.registerUser(userInfo,userInfoRepository);
            return "Account created";
        }
        return "Username already used";
    }



}
