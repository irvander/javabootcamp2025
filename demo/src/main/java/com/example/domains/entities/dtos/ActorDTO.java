package com.example.domains.entities.dtos;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name="Actor", description="Datos del actor")
public class ActorDTO {
	private int actorId;
	@Schema(description="nombre", minLength=2, maxLength=45, example="Ana")
	private String firstName;
	@Schema(description="apellido", minLength=2, maxLength=45, example="Perez")
	private String lastName;
	
	public static ActorDTO from(Actor source) {
		return new ActorDTO(source.getActorId(), source.getFirstName(), source.getLastName());
	}
	
	public static Actor from(ActorDTO source) {
		return new Actor(source.getActorId(), source.getFirstName(), source.getLastName());
	}
}