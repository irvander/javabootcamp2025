package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.FilmActorsRepository;
import com.example.domains.contracts.services.FilmActorsService;
import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmActorPK;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class FilmActorsServiceImpl implements FilmActorsService {
	private FilmActorsRepository repo;

	@Override
	public List<FilmActor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FilmActor> getOne(FilmActorPK id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public FilmActor add(FilmActor item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilmActor modify(FilmActor item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(FilmActor item) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(FilmActorPK id) {
		// TODO Auto-generated method stub
		
	}

}
