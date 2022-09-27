package com.recipe.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.recipe.RecipeManagerApplication;
import com.recipe.entity.Recipe;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RecipeManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	// test get recipe by recipeId
	@Test

	void testGetRecipe() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/recipes/1"), HttpMethod.GET, entity,
				String.class);
		String expected = "{\"recipeId\":1,\"type\":\"veg\",\"serve\":5,\"ingredent\":\"potatoes\",\"instruction\":\"oven\"}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	// test get recipe which is veg
	@Test

	void testGetAllVegRecipe() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> actual = restTemplate.exchange(createURLWithPort("/recipes/type?type=veg"),
				HttpMethod.GET, entity, String.class);
		// System.out.println(actual);
		String expected = "[{\"recipeId\":1,\"type\":\"veg\",\"serve\":5,\"ingredent\":\"potatoes\",\"instruction\":\"oven\"}]";

		JSONAssert.assertEquals(expected, actual.getBody(), false);
	}

	// test get recipe by serve and ingredient
	@Test

	void testGetRecipeByServeAndIngredient() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> actual = restTemplate.exchange(
				createURLWithPort("/recipes/?serve=5&ingredient=potatoes"), HttpMethod.GET, entity, String.class);
		String expected = "[{\"recipeId\":1,\"type\":\"veg\",\"serve\":5,\"ingredent\":\"potatoes\",\"instruction\":\"oven\"}]";

		JSONAssert.assertEquals(expected, actual.getBody(), false);
	}

	// test : get recipe by instruction not by ingredient
	@Test
	void testGetNotByIngredientAndInstruction() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> actual = restTemplate.exchange(createURLWithPort("/recipes/soleman/oven"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"recipeId\":1,\"type\":\"veg\",\"serve\":5,\"ingredent\":\"potatoes\",\"instruction\":\"oven\"}]";
		JSONAssert.assertEquals(expected, actual.getBody(), false);
	}

	// test for add a recipe
	@Test
	void testAddRecipe() throws JSONException {
		Recipe recipe = new Recipe("non-veg", 6, "soup", "Induction");
		HttpEntity<Recipe> entity = new HttpEntity<Recipe>(recipe, headers);
		ResponseEntity<String> actual = restTemplate.exchange(createURLWithPort("/recipes"), HttpMethod.POST, entity,
				String.class);
		String expected = "{\"recipeId\":4,\"type\":\"non-veg\",\"serve\":6,\"ingredent\":\"soup\",\"instruction\":\"Induction\"}";
		JSONAssert.assertEquals(expected, actual.getBody(), false);

	}

	// test for updating recipe by recipeId
	@Test
	void testUpdateRecipe() throws JSONException {
		Recipe recipe = new Recipe("non-veg", 6, "pasta", "stove");
		HttpEntity<Recipe> entity = new HttpEntity<Recipe>(recipe, headers);
		ResponseEntity<String> actual = restTemplate.exchange(createURLWithPort("/recipes/4"), HttpMethod.PUT, entity,
				String.class);
		String expected = "{\"recipeId\":4,\"type\":\"non-veg\",\"serve\":6,\"ingredent\":\"pasta\",\"instruction\":\"stove\"}";
		JSONAssert.assertEquals(expected, actual.getBody(), false);
	}
	// Create URL for testing
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}