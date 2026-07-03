package com.pokedex.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pokedex")
public class PokedexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApiApplication.class, args);
	}

}
