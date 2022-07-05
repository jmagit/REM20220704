package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findTop5ByFirstNameStartingWithOrderByFirstName(String prefijo);
	List<Actor> findByActorIdBetween(int vInicial, int vFinal);
	List<Actor> findByActorIdBetween(int vInicial, int vFinal, Sort orden);
	long countByActorIdLessThan(int valor);
}
