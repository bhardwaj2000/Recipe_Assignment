package com.recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RecipeManagerApplication is the main class to run spring boot application.
 * flow of working: controller->service->repository-> entity
 */
@SpringBootApplication
public class RecipeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeManagerApplication.class, args);

	}

}
