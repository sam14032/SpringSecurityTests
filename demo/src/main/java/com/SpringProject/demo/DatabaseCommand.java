package com.SpringProject.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DatabaseCommand {

    @Autowired
    UserInfoRepository userInfoRepository;
    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public void registerUser(UserInfo userInfo, UserInfoRepository userInfoRepository) {
        userInfoRepository.save(userInfo);
    }

    public String showAllUserEntries(UserInfoRepository userInfoRepository) {
        String userlist = "";
        for (UserInfo user : userInfoRepository.findAll()) {
            userlist += user.toString();
        }
        log.info("User info");
        log.info(userlist);
        return userlist;
    }

    public List<UserInfo> getAllUsersEntries(UserInfoRepository userInfoRepository) {
        List<UserInfo> userInfoList = new ArrayList<>();
        for (UserInfo user : userInfoRepository.findAll()) {
            userInfoList.add(user);
        }
        return userInfoList;
    }


    public UserInfo getUserByUsername(String username, UserInfoRepository userInfoRepository) {
        UserInfo userInfo = null;
        for (UserInfo user : userInfoRepository.findByUsername(username))
            userInfo = user;

        return userInfo;
    }

    public void display(String displayInfo) {
        log.info(displayInfo);
    }


}
