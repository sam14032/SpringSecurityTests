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

import java.util.Optional;

@Component
public class CustomAuthentification implements AuthenticationProvider {

	@Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> userInfo = userRepository.findById(username);
        User user = userInfo.get();
        if (user == null) {
            throw new BadCredentialsException("Username not found");
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Username or Password is invalid");
        }
        return new UsernamePasswordAuthenticationToken(username, passwordEncoder().encode(password), user.getAuthorities());
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
