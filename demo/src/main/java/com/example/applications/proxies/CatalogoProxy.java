package com.example.applications.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.applications.dtos.ActorRecibido;
import com.example.applications.dtos.Pelicula;

@FeignClient(name = "catalogo-service") 
//@FeignClient(name = "catalogo-service", url = "http://localhost:8010") 
public interface CatalogoProxy {

	@GetMapping(path = "/")
	String damaInicio();
	
	@GetMapping(path = "/v1/actores/{id}")
	ActorRecibido dameUnActor(@PathVariable int id);
	
	@GetMapping(path = "/v1/peliculas?mode=short")
	List<Pelicula> damePeliculas();
}
