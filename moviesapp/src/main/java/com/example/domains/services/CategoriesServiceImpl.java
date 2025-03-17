package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.CategoriesRepository;
import com.example.domains.contracts.services.CategoriesService;
import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class CategoriesServiceImpl implements CategoriesService {
	private CategoriesRepository dao;
	
	public CategoriesServiceImpl(CategoriesRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Category> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Category> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La categoría no puede ser nula");
		}
		if(item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsMessage());
		}
		if(item.getCategoryId() > 0 && dao.existsById(item.getCategoryId())) {
			throw new DuplicateKeyException("La categoría ya existe");
		}
		return dao.save(item);
	}

	@Override
	public Category modify(Category item) throws NotFoundException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La categoría no puede ser nula");
		}
		if(item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsMessage());
		}
		if(!dao.existsById(item.getCategoryId())) {
			throw new NotFoundException("El actor no existe");
		}
		return dao.save(item);
	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La categoría no puede ser nula");
		}
		dao.delete(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

}
