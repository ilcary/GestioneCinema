package com.example.carisiodigiampietro.configuration;

import java.time.LocalDate;

import com.example.carisiodigiampietro.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

	// CINEMA

	@Bean
	public Cinema cineMille() {
		return Cinema.builder()
				.name("Cine Mille")
				.build();

	}



	
}
