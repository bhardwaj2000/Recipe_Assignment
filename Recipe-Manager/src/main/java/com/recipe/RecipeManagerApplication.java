package com.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeManagerApplication.class, args);

		// flow of working:
		// controller->service->repository-> entity
	}

}
