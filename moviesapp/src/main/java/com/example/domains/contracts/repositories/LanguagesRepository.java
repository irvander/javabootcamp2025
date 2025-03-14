package com.example.domains.contracts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.domains.entities.Language;

public interface LanguagesRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language>{    

}
