package com.example.domains.entities.dtos;

import org.springframework.data.rest.core.config.Projection;

import com.example.domains.entities.Film;

@Projection(name = "peliculaCorta", types = { Film.class }) 
public interface FilmShort {
	int getFilmId();
	String getTitle();
}
