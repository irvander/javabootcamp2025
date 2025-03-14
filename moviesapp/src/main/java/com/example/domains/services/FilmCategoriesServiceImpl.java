package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.FilmCategoriesRepository;
import com.example.domains.contracts.services.FilmCategoriesService;
import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.FilmCategoryPK;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class FilmCategoriesServiceImpl implements FilmCategoriesService {
	private FilmCategoriesRepository repo;
	
	@Override
	public List<FilmCategory> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FilmCategory> getOne(FilmCategoryPK id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public FilmCategory add(FilmCategory item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilmCategory modify(FilmCategory item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(FilmCategory item) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(FilmCategoryPK id) {
		// TODO Auto-generated method stub
		
	}

}
