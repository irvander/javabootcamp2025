package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.entities.Category;

public interface CategoriesRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>{    
	List<Category> findByCategoryIdGreaterThan(int id);
}

