package com.example.demo.controller;

import com.example.demo.dto.ElementDto;
import com.example.demo.model.Element;
import com.example.demo.service.ElementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persistence/api/elements")
public class ElementController {

	private final ElementService elementService;

	public ElementController(ElementService elementService) {
		this.elementService = elementService;
	}

	@GetMapping
	public List<ElementDto> all() {
		return elementService.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ElementDto> get(@PathVariable String id) {
		return elementService.findById(id)
			.map(e -> ResponseEntity.ok(toDto(e)))
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ElementDto> update(@PathVariable String id, @Valid @RequestBody ElementDto dto) {
		Element element = new Element(id, dto.title, dto.description);
		Element saved = elementService.upsert(element);
		return ResponseEntity.ok(toDto(saved));
	}

	private ElementDto toDto(Element e) {
		ElementDto dto = new ElementDto();
		dto.id = e.getId();
		dto.title = e.getTitle();
		dto.description = e.getDescription();
		return dto;
	}
}


