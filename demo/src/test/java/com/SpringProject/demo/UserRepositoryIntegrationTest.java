package com.SpringProject.demo;

import com.SpringProject.demo.User.User;
import com.SpringProject.demo.User.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenGetOne_thenReturnUser() {
		// given
		User admin = new User("admin", "admin");
		entityManager.persist(admin);
		entityManager.flush();

		// when
		User found = userRepository.getOne(admin.getUsername());

		// then
		assertThat(found.getUsername()).isEqualTo(admin.getUsername());
	}

	@Test
	public void whenFindById_ThenReturnUser() {
		// given
		User admin = new User("admin", "admin");
		entityManager.persist(admin);
		entityManager.flush();

		// when
		Optional<User> found = userRepository.findById(admin.getUsername());

		// then
		assertThat(found.get()).isEqualTo(admin);
	}

	@Test
	public void modifyUserPassword() {
		// given
		String newPassword = "123456";
		User userToChangePassword = new User("passwordReceiver", "admin");
		entityManager.persist(userToChangePassword);
		entityManager.flush();

		// when
		User foundUser = userRepository.getOne(userToChangePassword.getUsername());
		foundUser.setPassword(newPassword);
		userRepository.save(foundUser);
		foundUser = userRepository.getOne(userToChangePassword.getUsername());

		// then
		assertThat(userRepository.count()).isEqualTo(1);
		assertThat(foundUser.passwordEncoder().matches(newPassword, foundUser.getPassword()));
	}

	@Test
	public void whenFindAll_thenReturnUsers() {
		// given
		List<User> givenUsers = Arrays.asList(
				new User("user1", "user1"),
				new User("user2", "user2"),
				new User("user3", "user3"),
				new User("user4", "user4"),
				new User("user5", "user5"));
		for (User user : givenUsers) {
			entityManager.persist(user);
		}
		entityManager.flush();

		// when
		List<User> foundUsers = userRepository.findAll();

		// then
		for (int i = 0; i < givenUsers.size(); ++i) {
			assertThat(foundUsers.get(i).getUsername()).isEqualTo(givenUsers.get(i).getUsername());
			assertThat(foundUsers.get(i).getPassword()).isEqualTo(givenUsers.get(i).getPassword());
		}
	}

	@Test
	public void countNbOfElements() {
		// given
		List<User> givenUsers = Arrays.asList(
				new User("user1", "user1"),
				new User("user2", "user2"),
				new User("user3", "user3"),
				new User("user4", "user4"),
				new User("user5", "user5"));
		for (User user : givenUsers) {
			entityManager.persist(user);
		}
		entityManager.flush();

		// when
		long nbOfElements = userRepository.count();

		// then
		assertThat(nbOfElements).isEqualTo(givenUsers.size());
	}

	@Test
	public void removeUser() {
		int indexOfUserToRemove = 0;
		// given
		List<User> givenUsers = Arrays.asList(
				new User("user1", "user1"),
				new User("user2", "user2"),
				new User("user3", "user3"),
				new User("user4", "user4"),
				new User("user5", "user5"));
		for (User user : givenUsers) {
			entityManager.persist(user);
		}
		entityManager.flush();

		// when
		userRepository.delete(givenUsers.get(indexOfUserToRemove));
		Optional<User> foundUsers = userRepository.findById(givenUsers.get(indexOfUserToRemove).getUsername());

		// then
		assertThat(foundUsers).isEqualTo(Optional.empty());
	}
}