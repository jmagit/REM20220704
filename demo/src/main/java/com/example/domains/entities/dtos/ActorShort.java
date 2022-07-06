package com.example.domains.entities.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorShort {
	private int actorId;
	@NotBlank
	@Length(min = 2, max = 45)
	private String firstName;
	private String lastName;

	public static Actor from(ActorShort source) {
		return new Actor(
				source.getActorId(),
				source.getFirstName(),
				source.getLastName()
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
