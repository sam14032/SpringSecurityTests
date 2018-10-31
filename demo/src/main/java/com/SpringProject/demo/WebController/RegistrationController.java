package com.SpringProject.demo.WebController;

import com.SpringProject.demo.DatabaseCommand;
import com.SpringProject.demo.UserInfo;
import com.SpringProject.demo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @RequestMapping(value="/register",consumes = "application/json",method=RequestMethod.POST)
    public String saveUserInfo(@RequestBody UserInfo userInfo)
    {
        DatabaseCommand databaseCommand = new DatabaseCommand();
        if (databaseCommand.getUserByUsername(userInfo.getUsername(),userInfoRepository) ==null)
        {
            databaseCommand.registerUser(userInfo,userInfoRepository);
            return "good";
        }
        return "Username already used";
    }



}
