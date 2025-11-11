package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
	private final Map<String, User> emailToUser = new ConcurrentHashMap<>();

	public UserService() {
		// Seed an admin and a regular user
		createUser("admin@example.com", "admin", Role.ADMIN);
		createUser("user@example.com", "user", Role.USER);
	}

	public Optional<User> findByEmail(String email) {
		return Optional.ofNullable(emailToUser.get(email.toLowerCase()));
	}

	public User createUser(String email, String password, Role role) {
		User user = new User(email, password, role);
		emailToUser.put(email.toLowerCase(), user);
		return user;
	}

	public boolean resetPassword(String email, String newPassword) {
		User user = emailToUser.get(email.toLowerCase());
		if (user == null) {
			return false;
		}
		user.setPassword(newPassword);
		return true;
	}

	public Optional<User> validateLogin(String email, String password) {
		User user = emailToUser.get(email.toLowerCase());
		if (user != null && Objects.equals(user.getPassword(), password)) {
			return Optional.of(user);
		}
		return Optional.empty();
	}
}


