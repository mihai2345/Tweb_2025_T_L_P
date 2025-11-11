package com.example.demo.service;

import com.example.demo.model.Element;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ElementService {
	private final Map<String, Element> idToElement = new ConcurrentHashMap<>();

	public ElementService() {
		// Seed a few elements
		idToElement.put("1", new Element("1", "Laptop", "A powerful laptop"));
		idToElement.put("2", new Element("2", "Phone", "A modern smartphone"));
		idToElement.put("3", new Element("3", "Tablet", "A lightweight tablet"));
	}

	public List<Element> findAll() {
		return new ArrayList<>(idToElement.values());
	}

	public Optional<Element> findById(String id) {
		return Optional.ofNullable(idToElement.get(id));
	}

	public Element upsert(Element element) {
		if (element.getId() == null || element.getId().isEmpty()) {
			String id = UUID.randomUUID().toString();
			element.setId(id);
		}
		idToElement.put(element.getId(), element);
		return element;
	}
}


