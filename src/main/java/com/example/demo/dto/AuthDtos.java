package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDtos {
	public static class LoginRequest {
		@Email
		@NotBlank
		public String email;
		@NotBlank
		public String password;
	}

	public static class SignupRequest {
		@Email
		@NotBlank
		public String email;
		@NotBlank
		public String password;
		public boolean admin;
	}

	public static class ResetPasswordRequest {
		@Email
		@NotBlank
		public String email;
		@NotBlank
		public String newPassword;
	}

	public static class AuthResponse {
		public String userId;
		public String email;
		public Role role;
	}
}


