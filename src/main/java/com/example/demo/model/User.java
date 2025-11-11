package com.example.demo.model;

import java.util.UUID;

public class User {
	private String id;
	private String email;
	private String password;
	private Role role;

	public User() {
	}

	public User(String email, String password, Role role) {
		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}


