package com.recipe.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipe.entity.Recipe;

@SpringBootTest
class RecipeRepositoryTest {

	@Autowired
	private RecipeRepository repository;

	// test : fetch all veg recipe
	@Test
	void testFindByVeg() {

		String expectedList = "Recipe [recipeId=1, veg=veg, serve=5, ingredent=potatoes, instruction=oven]";
		List<Recipe> byVeg = repository.findByVeg("veg");
		String actuallist = byVeg.stream().map(Object::toString).collect(Collectors.joining(", "));
		assertThat(actuallist).isEqualTo(expectedList);

	}

	// test : fetch by serve and ingredient
	@Test
	void testFindByServeAndIngredent() {
		String expectedList = "Recipe [recipeId=1, veg=veg, serve=5, ingredent=potatoes, instruction=oven]";
		List<Recipe> byServeAndIngredent = repository.findByServeAndIngredent(5, "potatoes");
		String actuallist = byServeAndIngredent.stream().map(Object::toString).collect(Collectors.joining(", "));
		assertThat(actuallist).isEqualTo(expectedList);
	}

	// test : fetch by instruction but given ingredient is not present
	@Test
	void testFindByInstructionAndNotByIngredent() {
		String expectedList = "Recipe [recipeId=1, veg=veg, serve=5, ingredent=potatoes, instruction=oven]";
		List<Recipe> byInstructionAndNotByIngredent = repository.findByInstructionAndNotByIngredent("soleman", "oven");
		String actuallist = byInstructionAndNotByIngredent.stream().map(Object::toString)
				.collect(Collectors.joining(", "));
		assertThat(actuallist).isEqualTo(expectedList);
	}
}