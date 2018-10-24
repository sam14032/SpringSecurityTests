package com.SpringProject.demo.Security;

import com.SpringProject.demo.DatabaseCommand;
import com.SpringProject.demo.UserInfo;
import com.SpringProject.demo.UserInfoRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class CustomAuthentification implements AuthenticationProvider {

    static UserInfoRepository userInfoRepository;

    public static void config(UserInfoRepository userInfoRepository) {
        CustomAuthentification.userInfoRepository = userInfoRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DatabaseCommand databaseCommand = new DatabaseCommand();
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserInfo userInfo = databaseCommand.getUserByUsername(username, userInfoRepository);
        if (userInfo == null) {
            throw new BadCredentialsException("Username not found");
        }
        if (!password.equals(userInfo.getPassword())) {
            throw new BadCredentialsException("Username or Password is invalid");
        }
        return new UsernamePasswordAuthenticationToken(username, passwordEncoder().encode(password), userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
