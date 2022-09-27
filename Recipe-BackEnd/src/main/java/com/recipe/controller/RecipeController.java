package com.recipe.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recipe.entity.Recipe;
import com.recipe.service.RecipeService;

/**
 * The {@link RecipeController} class handle the all the RestApi
 * {@link CrossOrigin} this is used here for passing data to client side
 * {@link RestController} this is used for creating bean for Controller
 */
@RestController
@CrossOrigin(origins = "*")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	private static Logger logger = LoggerFactory.getLogger(RecipeController.class);

	// Fetch all recipe
	// http://localhost:3050/recipes
	@GetMapping("/recipes")
	public ResponseEntity<List<Recipe>> getAllRecipe() {
		logger.info("Present in RecipeController!! getAllRecipe!!");
		List<Recipe> recipeList = recipeService.getAllRecipe();
		if (recipeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.FOUND).body(recipeList);
	}

	// Fetch recipe by recipeId
	// http://localhost:3050/recipes/1
	@GetMapping("/recipes/{id}")
	public ResponseEntity<Object> getRecipe(@PathVariable("id") int id) {
		logger.info("Present in RecipeController!! getRecipe!!");
		Recipe recipe = recipeService.getRecipe(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(recipe);

	}

	// fetch all veg recipe
	// http://localhost:3050/recipes/type?veg=veg
	@GetMapping("/recipes/type")
	public ResponseEntity<List<Recipe>> getAllVegRecipe(@RequestParam(value = "type") String type) {
		logger.info("Present in RecipeController!! UpdateRecipe!!");
		List<Recipe> vegrecipeList = recipeService.getVegRecipe(type);
		if (vegrecipeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.FOUND).body(vegrecipeList);
	}

	// get recipe by serve and ingredient
	// http://localhost:3050/recipes/?serve=5&ingredient=soleman
	@GetMapping("/recipes/")
	public ResponseEntity<List<Recipe>> getRecipeByServeAndIngredient(@RequestParam(value = "serve") int serve,
			@RequestParam(value = "ingredient") String ingredient) {
		logger.info("Present in RecipeController!! getRecipeByServeAndIngredient!!");
		List<Recipe> matchRecipeList = recipeService.getServeAndIngerdient(serve, ingredient);
		if (matchRecipeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.status(HttpStatus.FOUND).body(matchRecipeList);
	}

	// get recipe by not ingredient and instruction
	// http://localhost:3050/recipes/soleman/induction
	@GetMapping("/recipes/{ingre}/{instr}")
	public ResponseEntity<List<Recipe>> getByIngredientAndInstruction(@PathVariable("ingre") String ingre,
			@PathVariable("instr") String instr) {
		logger.info("Present in RecipeController!! getByIngredientAndInstruction!!");
		List<Recipe> matchRecipeList = recipeService.getRecipeByInstructionNotIngredient(ingre, instr);
		if (matchRecipeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		return ResponseEntity.status(HttpStatus.FOUND).body(matchRecipeList);
	}

	// add new recipe , post method
	// http://localhost:3050/recipes
	@PostMapping("/recipes")
	public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
		logger.info("Present in RecipeController!! addRecipe!!");
		Recipe recipeAdded = recipeService.addRecipe(recipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(recipeAdded);

	}

	// update existing recipe by recipeId, put method
	// http://localhost:3050/recipes/1
	@PutMapping("/recipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable("id") int id) {

		logger.info("Present in RecipeController!! UpdateRecipe!!");
		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedRecipe);

	}

	// delete recipe by recipeId, delete method
	// http://localhost:3050/recipes/1
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<Object> deleteRecipe(@PathVariable("id") int id) {
		logger.info("Present in RecipeController!! DeleteRecipe!!");
		recipeService.deleteRecipe(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Recipe deleted with id : " + id);

	}

}