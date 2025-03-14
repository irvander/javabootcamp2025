package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.FilmActorsPKRepository;
import com.example.domains.contracts.services.FilmActorsPKService;
import com.example.domains.entities.FilmActorPK;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class FilmActorsPKServiceImpl implements FilmActorsPKService {
	private FilmActorsPKRepository repo;
	
	@Override
	public List<FilmActorPK> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FilmActorPK> getOne(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public FilmActorPK add(FilmActorPK item) throws DuplicateKeyException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilmActorPK modify(FilmActorPK item) throws NotFoundException, InvalidDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(FilmActorPK item) throws InvalidDataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
