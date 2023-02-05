package com.example.carisiodigiampietro;

import com.example.carisiodigiampietro.entity.*;
import com.example.carisiodigiampietro.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.carisiodigiampietro.configuration.Config;

@SpringBootApplication
public class ProgettoSpringBootApplication implements CommandLineRunner{

	@Autowired
	CinemaService cs;

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSpringBootApplication.class, args);
				
	}
	
	@Override
	public void run(String... args) throws Exception {
		//configGestioneCinema();
	}
	
	public void configGestioneCinema() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

		Cinema cineMille = ac.getBean("cineMille", Cinema.class);
		cs.save(cineMille);
		
		((AnnotationConfigApplicationContext) ac).close();

	}


}
