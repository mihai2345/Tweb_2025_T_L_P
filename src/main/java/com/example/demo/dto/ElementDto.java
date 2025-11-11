package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class ElementDto {
	public String id;
	@NotBlank
	public String title;
	public String description;
}


