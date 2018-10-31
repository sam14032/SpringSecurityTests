package com.SpringProject.demo.WebController;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/register", consumes = "application/json", method = RequestMethod.POST)
	public HttpStatus saveUserInfo(@RequestBody User user) {
		if (!userRepository.existsById(user.getUsername())) {
			userRepository.save(user);
			return HttpStatus.OK;
		}
		return HttpStatus.BAD_REQUEST;
	}


}
