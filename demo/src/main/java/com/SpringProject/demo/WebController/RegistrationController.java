package com.SpringProject.demo.WebController;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class RegistrationController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	public String saveUserInfo(@RequestBody User user) {
		if (userRepository.getUserByUsername(user.getUsername()) == null) {
			userRepository.save(user);
			return "Account created";
		}
		return "Username already used";
	}


}
