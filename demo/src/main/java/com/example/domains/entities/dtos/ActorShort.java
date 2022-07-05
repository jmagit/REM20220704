package com.example.domains.entities.dtos;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorShort {
	private int id;
	private String nombre;
	private String apellidos;

	public static Actor from(ActorShort source) {
		return new Actor(
				source.getId(),
				source.getNombre(),
				source.getApellidos()
				);
	}
	public static ActorShort from(Actor source) {
		return new ActorShort(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
				);
	}
}
