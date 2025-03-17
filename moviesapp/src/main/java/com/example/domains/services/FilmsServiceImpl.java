package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.FilmsRepository;
import com.example.domains.contracts.services.FilmsService;
import com.example.domains.entities.Film;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class FilmsServiceImpl implements FilmsService {
	private FilmsRepository dao;
	
	public FilmsServiceImpl(FilmsRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Film> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Film> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La película no puede ser nula");
		}
		if(item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsMessage());
		}
		if(item.getFilmId() > 0 && dao.existsById(item.getFilmId())) {
			throw new DuplicateKeyException("La película ya existe");
		}
		return dao.save(item);
	}

	@Override
	public Film modify(Film item) throws NotFoundException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La película no puede ser nula");
		}
		if(item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsMessage());
		}
		if(!dao.existsById(item.getFilmId())) {
			throw new NotFoundException("La película no existe");
		}
		return dao.save(item);
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La película no puede ser nula");
		}
		dao.delete(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
