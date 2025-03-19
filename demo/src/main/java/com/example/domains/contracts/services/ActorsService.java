package com.example.domains.contracts.services;

import com.example.domains.entities.Actor;
import com.example.domains.core.contracts.services.DomainService;
import com.example.domains.core.contracts.services.ProjectionDomainService;

public interface ActorsService extends ProjectionDomainService<Actor, Integer>{
	void repartePremios();
}
