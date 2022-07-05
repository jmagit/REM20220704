package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
	List<Actor> findTop5ByFirstNameStartingWithOrderByFirstName(String prefijo);
	List<Actor> findByActorIdBetween(int vInicial, int vFinal);
	List<Actor> findByActorIdBetween(int vInicial, int vFinal, Sort orden);
	long countByActorIdLessThan(int valor);
	@Query("SELECT a FROM Actor a where a.actorId > ?1")
	List<Actor> consulta(int id);
	@Query(value =  "select * from actor where actor_id > ?1", nativeQuery = true)
	List<Actor> consultaSQL(int id);
}
