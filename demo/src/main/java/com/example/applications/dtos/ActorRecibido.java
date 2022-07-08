package com.example.applications.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ActorRecibido {
	private int id;
	private String nombre;
	private String apellidos;
}
