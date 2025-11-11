package com.example.demo.controller;

import com.example.demo.dto.AuthDtos;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persistence/api/auth")
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthDtos.LoginRequest request) {
		return userService.validateLogin(request.email, request.password)
			.<ResponseEntity<?>>map(user -> ResponseEntity.ok(toAuthResponse(user)))
			.orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody AuthDtos.SignupRequest request) {
		if (userService.findByEmail(request.email).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		Role role = request.admin ? Role.ADMIN : Role.USER;
		User user = userService.createUser(request.email, request.password, role);
		return ResponseEntity.status(HttpStatus.CREATED).body(toAuthResponse(user));
	}

	@PostMapping("/reset")
	public ResponseEntity<?> reset(@Valid @RequestBody AuthDtos.ResetPasswordRequest request) {
		boolean ok = userService.resetPassword(request.email, request.newPassword);
		if (!ok) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.ok("Password updated");
	}

	private AuthDtos.AuthResponse toAuthResponse(User user) {
		AuthDtos.AuthResponse res = new AuthDtos.AuthResponse();
		res.userId = user.getId();
		res.email = user.getEmail();
		res.role = user.getRole();
		return res;
	}
}


