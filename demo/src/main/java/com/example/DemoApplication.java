package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.repositories.ActorsRepository;
import com.example.domains.entities.Actor;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;


@SpringBootApplication
//@ComponentScan(basePackages = "com.example.ioc")
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		exampleData();
	}
	
	@Autowired
	private ActorsRepository dao;
	
	private void exampleData() throws InvalidDataException {	
		var actor = new Actor(202, "Emilie", "Valey");
		dao.delete(actor);

		dao.findAll().forEach(System.err::println);
		
		//var actor = new Actor(0, "Emilie", "Valey");
		//dao.save(actor);
/*		var item = dao.findById(201);
		if(item.isPresent()) {
			var actor = item.get();
			actor.setFirstName("EMILIE");
			actor.setLastName(actor.getLastName().toUpperCase());
			dao.save(actor);
		} else {
			System.err.println("No se ha encontrado el actor.");
		}
		
		dao.findAll().forEach(System.err::println);
		dao.deleteById(201);
		dao.findAll().forEach(System.err::println);
	
		dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("e").forEach(System.err::println);
		dao.findTop10ByFirstNameStartingWith("e", Sort.by("FirstName").ascending()).forEach(System.err::println);

		dao.findByActorIdGreaterThan(198).forEach(System.err::println);
		dao.findNovedadesJPQL(198).forEach(System.err::println);
		dao.findNovedadesSQL(198).forEach(System.err::println);
*/	
		//dao.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("actorId"), 5), null).forEach(System.err::println);
	
		
	}
	
}