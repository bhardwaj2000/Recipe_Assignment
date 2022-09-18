package com.recipe.controller;

import java.util.List;

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

@RestController
@CrossOrigin(origins = "*")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	// Fetch all recipe
	// http://localhost:3050/recipes
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipe() {
		List<Recipe> recipeList = recipeService.getAllRecipe();

		return recipeList;
	}

	// Fetch recipe by recipeId
	// http://localhost:3050/recipes/1
	@GetMapping("/recipes/{id}")
	public Object getRecipe(@PathVariable("id") int id) {

		Recipe recipe = recipeService.getRecipe(id);
		if (recipe == null) {

			return "no recipe with given id";
		}
		return recipe;

	}

	// fetch all veg recipe
	// http://localhost:3050/recipes/veg?veg=veg
	@GetMapping("/recipes/veg")
	public List<Recipe> getAllVegRecipe(@RequestParam(value = "veg") String veg) {
		List<Recipe> vegrecipeList = recipeService.getVegRecipe(veg);
		return vegrecipeList;

	}

	// get recipe by serve and ingredient
	// http://localhost:3050/recipes/?serve=5&ingredient=soleman
	@GetMapping("/recipes/")
	public List<Recipe> getRecipeByServeAndIngredient(@RequestParam(value = "serve") int serve,
			@RequestParam(value = "ingredient") String ingredient) {
		List<Recipe> matchRecipeList = recipeService.getServeAndIngerdient(serve, ingredient);
		return matchRecipeList;
	}

	// get recipe by not ingredient and instruction
	// http://localhost:3050/recipes/soleman/induction
	@GetMapping("/recipes/{ingre}/{instr}")
	public ResponseEntity<List<Recipe>> getByIngredientAndInstruction(@PathVariable("ingre") String ingre,
			@PathVariable("instr") String instr) {
		List<Recipe> matchRecipeList = recipeService.getRecipeByInstructionNotIngredient(ingre, instr);
		if (matchRecipeList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
		return ResponseEntity.status(HttpStatus.FOUND).body(matchRecipeList);
	}

	// add new recipe , post method
	// http://localhost:3050/recipes
	@PostMapping("/recipes")
	public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
		Recipe recipeAdded = null;
		try {

			recipeAdded = recipeService.addRecipe(recipe);
			return ResponseEntity.status(HttpStatus.CREATED).body(recipeAdded);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update existing recipe by recipeId, put method
	// http://localhost:3050/recipes/1
	@PutMapping("/recipes/{id}")
	public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable("id") int id) {
		try {
			this.recipeService.updateRecipe(recipe, id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(recipe);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// delete recipe by recipeId, delete method
	// http://localhost:3050/recipes/1
	@DeleteMapping("/recipes/{id}")
	public ResponseEntity<Object> deleteRecipe(@PathVariable("id") int id) {
		try {
			recipeService.deleteRecipe(id);
			return ResponseEntity.ok().body("Recipe deleted with id : " + id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id not found");
		}
	}

}