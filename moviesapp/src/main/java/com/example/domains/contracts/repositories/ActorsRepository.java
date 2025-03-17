package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Actor;

public interface ActorsRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor>{    
	List<Actor> findTop5ByFirstNameStartingWithOrderByLastNameDesc(String prefix);
	List<Actor> findTop10ByFirstNameStartingWith(String prefix, Sort orderBy);
	
	List<Actor> findByActorIdGreaterThan(int id);
	@Query(value = "SELECT * FROM actor a WHERE a.actor_Id > :id", nativeQuery = true)
	List<Actor> findNovedadesSQL(int id);
}
