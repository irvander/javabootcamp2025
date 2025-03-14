package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import com.example.domains.contracts.repositories.FilmCategoriesPKRepository;
import com.example.domains.contracts.services.FilmCategoriesPKService;
import com.example.domains.entities.FilmCategoryPK;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

public class FilmCategoriesPKServiceImpl implements FilmCategoriesPKService {
		private FilmCategoriesPKRepository repo;

		@Override
		public List<FilmCategoryPK> getAll() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Optional<FilmCategoryPK> getOne(Integer id) {
			// TODO Auto-generated method stub
			return Optional.empty();
		}

		@Override
		public FilmCategoryPK add(FilmCategoryPK item) throws DuplicateKeyException, InvalidDataException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FilmCategoryPK modify(FilmCategoryPK item) throws NotFoundException, InvalidDataException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void delete(FilmCategoryPK item) throws InvalidDataException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteById(Integer id) {
			// TODO Auto-generated method stub
			
		}

}
