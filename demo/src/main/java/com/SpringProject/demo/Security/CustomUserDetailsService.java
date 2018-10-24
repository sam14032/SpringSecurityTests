package com.SpringProject.demo.Security;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	static UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userInfo = userRepository.getUserByUsername(username);
		if (userInfo == null) {
			throw new BadCredentialsException("Username not found");
		}
		return userInfo;
	}


}
