package com.SpringProject.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This interface extends Spring Data JPA’s JpaRepository, specifying the
// domain type as User and the id type as Long. This interface, though
// empty on the surface, packs a punch given it supports:
//		Creating new instances
//		Updating existing ones
//		Deleting
//		Finding (one, all, by simple or complex properties)
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsername(String username);

	User getUserByUsername(String username);
}
