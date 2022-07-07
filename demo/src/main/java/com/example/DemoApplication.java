package com.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.dtos.ActorShort;
import com.example.domains.entities.dtos.ActorShort2;
import com.example.domains.entities.dtos.NamesOnly;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("Aplicación arrancada");
//		consultas();
	}

	@Autowired
	ActorRepository dao;
	@Autowired
	ActorService srv;
	
	@Transactional
	private void consultas() {
//		var ele = dao.findById(1);
//		if(ele.isPresent()) {
//			var actor = ele.get();
//			actor.setFirstName(actor.getFirstName().toUpperCase());
//			dao.save(actor);
//		}
//		var actor = new Actor(0, "Pepito", "Grillo");
//		dao.save(actor);
//		dao.deleteById(219);
//		dao.findAll().forEach(item -> System.out.println(item));
//		dao.findByActorIdBetween(5,10).forEach(item -> System.out.println(item));
//		dao.findTop5ByFirstNameStartingWithOrderByFirstName("p").forEach(item -> System.out.println(item));
//		System.out.println(dao.countByActorIdLessThan(10));
//		dao.findAll(Sort.by("firstName","lastName")).forEach(item -> System.out.println(item));
//		dao.findByActorIdBetween( 5, 10, Sort.by("firstName","lastName")).forEach(item -> System.out.println(item));
//		dao.findAll(PageRequest.of(5, 10, Sort.by("actorId"))).getContent().forEach(item -> System.out.println(item));
//		dao.consultaSQL(200).forEach(item -> System.out.println(item));
//		var ele = dao.findById(1);
//		if(ele.isPresent()) {
//			var actor = ele.get();
//			actor.getFilmActors().forEach(item -> System.out.println(item.getFilm().getTitle()));
//		}
//		var actor = new Actor(0, "PEPITO", "12345678Z");
//		if(actor.isInvalid())
//			System.out.println(actor.getErrorsMessage());
//		else
//			System.out.println(ActorShort.from(actor));
//		//dao.save(actor);
////		var a = new ActorShort();
//		System.out.println(ActorShort.from(new ActorShort(1, "Desde", "DTO")));
//		dao.findAll().stream().map(item -> ActorShort.from(item)).toList().forEach(System.out::println);
//		dao.findTop5ByFirstNameStartingWithOrderByFirstName("p").forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findByActorIdNotNull(NamesOnly.class).forEach(item -> System.out.println(item.getId() + " " + item.getNombre()));
//		dao.findByActorIdIsNotNull(ActorShort.class).forEach(item -> System.out.println(item));
//		
//		try {
//			srv.add(new Actor(0, "PEPITO", "12345678Z"));
//		} catch (DuplicateKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		srv.getAll().forEach(System.out::println);
	}
	
	@Bean
	public Docket api() {                
   	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.example.application.resource"))
	      .paths(PathSelectors.ant("/**"))
	      .build()
	      .apiInfo(new ApiInfoBuilder()
	                .title("Microservicio: Demos")
	                .description("Demos de Microservicios.")
	                .version("1.0")
	                .license("Apache License Version 2.0")
	                .contact(new Contact("Yo Mismo", "http://www.example.com", "myeaddress@example.com"))
	                .build());
	}

}
