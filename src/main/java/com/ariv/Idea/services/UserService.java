package com.ariv.Idea.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariv.Idea.models.User;
import com.ariv.Idea.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;

	public User findById(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}

	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return this.uRepo.save(user);
	}

	public User getUserByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}

	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if (user == null)
			return false;

		return BCrypt.checkpw(password, user.getPassword());
	}
}
