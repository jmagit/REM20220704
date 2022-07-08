package com.example.applications.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@RestController
@RequestMapping("/v1/consultas")
public class ConsultasResource {
	@Autowired
	RestTemplate srvRest;
	
	@GetMapping
	public String getInicio() {
		return srvRest.getForObject("http://localhost:8010/", String.class);
	}
	
	@Data @AllArgsConstructor @NoArgsConstructor
	static class ActorRecibido {
		private int id;
		private String nombre;
		private String apellidos;
	}
	
	@GetMapping(path = "/uno/{id}")
	public ActorRecibido dameUno(@PathVariable int id) {
		return srvRest.getForObject("http://localhost:8010/v1/actores/{id}", ActorRecibido.class, id);
	}

	@Data @AllArgsConstructor @NoArgsConstructor
	static class Pelicula {
		private int filmId;
		private String title;
	}

	@GetMapping(path = "/muchos")
	public List<Pelicula> dameMuchos() {
		ResponseEntity<List<Pelicula>> response = srvRest.exchange(
					"http://localhost:8010/v1/peliculas?mode=short", 
					HttpMethod.GET,
					HttpEntity.EMPTY, 
					new ParameterizedTypeReference<List<Pelicula>>() {
				});

		return response.getBody();
	}
	
}
