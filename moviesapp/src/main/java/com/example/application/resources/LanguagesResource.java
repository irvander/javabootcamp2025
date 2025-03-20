package com.example.application.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.dtos.LanguageDTO;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/idiomas/v1")
public class LanguagesResource {
	
	private LanguagesService srv;
	
	public LanguagesResource(LanguagesService srv) {
		super();
		this.srv = srv;
	}
	
	@GetMapping
	@Hidden
	public List<LanguageDTO> getAll() {
		return srv.getByProjection(LanguageDTO.class);
	}
	

	@GetMapping(path = "/{id}")
	@Operation(summary = "Obtiene un idioma por su id")
	public LanguageDTO getOne(@PathVariable @Parameter(description = "Identificador del idioma") int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty()) {
			throw new NotFoundException("No se encontró el elemento con id " + id);
		}
		return LanguageDTO.from(item.get());
	}

}
