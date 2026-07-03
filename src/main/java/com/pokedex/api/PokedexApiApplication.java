package com.pokedex.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.pokedex")
@EnableJpaRepositories(basePackages = "com.pokedex.persistence.repository.relational")
@EnableMongoRepositories(basePackages = "com.pokedex.persistence.repository.document")
public class PokedexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApiApplication.class, args);
	}

}
