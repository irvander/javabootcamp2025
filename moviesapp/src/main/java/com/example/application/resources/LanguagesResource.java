package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.LanguagesService;
import com.example.domains.entities.dtos.LanguageDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

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
	public LanguageDTO getOne(@PathVariable @Parameter(description = "Identificador del idioma") int id)
			throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró el elemento con id " + id);
		}
		return LanguageDTO.from(item.get());
	}

	@PostMapping
	@ApiResponse(responseCode = "201", description = "Idioma creado")
	public ResponseEntity<Object> create(@Valid @RequestBody LanguageDTO item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(LanguageDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
