package com.SpringProject.demo.User;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
	private final UserRepository repository;
	private final UserResourceAssembler assembler;

	public UserController(UserRepository repository, UserResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/users")
	Resources<Resource<User>> all() {
		List<Resource<User>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());
		return new Resources<>(users,
				linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@PostMapping("/users")
	ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {
		Resource<User> resource = assembler.toResource(repository.save(newUser));

		return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@GetMapping("/users/{id}")
	Resource<User> one(@PathVariable String id) {
		User user = repository.findById(id).orElseThrow(UserNotFoundException::new);
		return assembler.toResource(user);
	}

	@PutMapping("/users/{id}")
	ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable String id) throws URISyntaxException {
		User updatedUser = repository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setPassword(newUser.getPassword());
					return repository.save(user);
				}).orElseGet(() -> {
					newUser.setUsername(id);
					return repository.save(newUser);
				});

		Resource<User> resource = assembler.toResource(updatedUser);

		return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/users/{id}")
	ResponseEntity<?> deleteUser(@PathVariable String id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
