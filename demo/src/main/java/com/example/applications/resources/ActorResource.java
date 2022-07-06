package com.example.applications.resources;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.dtos.ActorShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/actores")
public class ActorResource {
	@Autowired
	private ActorService srv;

	@GetMapping
	public List<ActorShort> getAll() {
		return srv.getByProjection(ActorShort.class);
	}

	@GetMapping(params = "page")
	public Page<ActorShort> getAll(Pageable page) {
		return srv.getByProjection(page, ActorShort.class);
	}

	@GetMapping(path = "/{id:\\d+}/**")
	public ActorShort getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
			
		return ActorShort.from(item.get());
	}
	
	@GetMapping(path = "/{id:\\d+}/peliculas")
	@Transactional
	public List<Film> getPeliculas(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
			
		return item.get().getFilmActors().stream().map(o -> o.getFilm()).toList();
	}
	
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody ActorShort item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = ActorShort.from(item);
		newItem = srv.add(newItem);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody ActorShort item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item.getActorId() != id) 
			throw new BadRequestException("No coinciden los identificadores");
		srv.modify(ActorShort.from(item));
	}

	@PutMapping("/{id}/jubilacion")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void jubilacion(@PathVariable int id) throws BadRequestException, NotFoundException, InvalidDataException {
		// ...
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}

