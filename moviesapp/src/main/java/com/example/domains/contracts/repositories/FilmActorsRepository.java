package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.entities.FilmActor;
import com.example.domains.entities.FilmActorPK;

public interface FilmActorsRepository extends JpaRepository<FilmActor, FilmActorPK>, JpaSpecificationExecutor<FilmActor>{    

}
