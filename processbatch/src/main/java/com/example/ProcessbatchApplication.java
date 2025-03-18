package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessbatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProcessbatchApplication.class, args);
	}

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	private Job personasJob;
	
	@Override
	public void run(String... args) throws Exception {

	}
}
