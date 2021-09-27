package com.ariv.Idea.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ariv.Idea.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	 User findByEmail(String email);
	 boolean existsByEmail(String email);
	 User findByName (String name);
}
