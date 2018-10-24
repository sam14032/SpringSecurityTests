package com.SpringProject.demo.Security;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthentification implements AuthenticationProvider {

	@Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User userInfo = userRepository.getUserByUsername(username);
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
