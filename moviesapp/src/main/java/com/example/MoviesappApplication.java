package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.repositories.LanguagesRepository;

@SpringBootApplication
public class MoviesappApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MoviesappApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		
		//example();
		
	}


	@Autowired
	private LanguagesRepository dao;
	
	private void example() {
		dao.findAll().forEach(System.err::println);
	}
}
	