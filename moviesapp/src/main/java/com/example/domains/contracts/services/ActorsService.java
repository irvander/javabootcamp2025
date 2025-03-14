package com.example.domains.contracts.services;

import com.example.domains.entities.Actor;
import com.example.domains.core.contracts.services.DomainService;

public interface ActorsService extends DomainService<Actor, Integer>{
	void repartePremios();
}