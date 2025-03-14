package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.entities.FilmCategoryPK;

public interface FilmCategoriesPKRepository extends JpaRepository<FilmCategoryPK, Integer>, JpaSpecificationExecutor<FilmCategoryPK> {
	
}