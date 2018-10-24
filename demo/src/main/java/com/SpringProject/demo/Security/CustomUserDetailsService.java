package com.SpringProject.demo.Security;

import com.SpringProject.demo.DatabaseCommand;
import com.SpringProject.demo.UserInfo;
import com.SpringProject.demo.UserInfoRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Service
public class CustomUserDetailsService implements UserDetailsService {


    static UserInfoRepository userInfoRepository;

    public static void Init(UserInfoRepository userInfoRepository){
        CustomUserDetailsService.userInfoRepository = userInfoRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DatabaseCommand databaseCommand = new DatabaseCommand();

        UserInfo userInfo = databaseCommand.getUserByUsername(username,userInfoRepository);
        if(userInfo == null){
            throw new BadCredentialsException("Username not found");
        }
        return userInfo;
    }


}
