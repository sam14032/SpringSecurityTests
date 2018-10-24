package com.SpringProject.demo.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadUserDatabase {
	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository) {
		return args -> {
			log.info("Preloading " + userRepository.save(new User("Streetlamp", "admin")));
			log.info("Preloading " + userRepository.save(new User("sam140258","password")));
		};
	}
}