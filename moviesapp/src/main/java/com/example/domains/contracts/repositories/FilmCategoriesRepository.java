package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.entities.FilmCategory;
import com.example.domains.entities.FilmCategoryPK;


public interface FilmCategoriesRepository extends JpaRepository<FilmCategory, FilmCategoryPK>, JpaSpecificationExecutor<FilmCategory>{    

}
