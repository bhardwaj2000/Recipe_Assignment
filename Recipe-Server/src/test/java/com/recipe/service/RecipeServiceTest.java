package com.recipe.service;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.recipe.entity.Recipe;
import com.recipe.repository.RecipeRepository;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

	@Mock
	private RecipeRepository repository;

	private RecipeService service;

	@BeforeEach
	void setUp() {
		this.service = new RecipeServiceImpl(this.repository);

	}

	// test get all recipe service
	@Test
//	@Disabled
	void testGetAllRecipe() {
		service.getAllRecipe();
		verify(repository).findAll();
	}

	// test get recipebyId service
	@Test
//	@Disabled
	void testGetRecipe() {
		int recipeId = 2;
		service.getRecipe(recipeId);

		try {
			verify(repository).findById(recipeId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Test AddRecipe Service
	@Test
//	@Disabled
	void testAddRecipe() {
		Recipe recipe = new Recipe("non-veg", 3, "egg", "stove");
		service.addRecipe(recipe);
		verify(repository).save(recipe);

	}

	// Test Update Recipe Service
//	@Disabled
	@Test
	void testUpdateRecipe() {
		Recipe recipe = new Recipe("non-veg", 3, "egg", "stove");
		service.updateRecipe(recipe, 3);
		verify(repository).save(recipe);
	}

//	@Disabled
	@Test
	void testDeleteRecipe() {
		int recipeId = 3;
		service.deleteRecipe(recipeId);
		verify(repository).deleteById(recipeId);
	}

	// Test getAll VegRecipe Service
//	@Disabled
	@Test
	void testGetVegRecipe() {
		String veg = "veg";
		service.getVegRecipe(veg);
		verify(repository).findByType(veg);
	}

	// Test Get Recipe By Serve And Ingredient service
//	@Disabled
	@Test
	void testGetServeAndIngerdient() {
		int serve = 5;
		String ingredient = "potatoes";
		service.getServeAndIngerdient(serve, ingredient);
		verify(repository).findByServeAndIngredent(serve, ingredient);
	}

	// Test Get Recipe by Instruction Not Ingredient Service
//	@Disabled
	@Test
	void testGetUniqueRecipe() {
		String ingredient = "soleman", recipe = "oven";
		service.getRecipeByInstructionNotIngredient(ingredient, recipe);
		verify(repository).findByInstructionAndNotByIngredent(ingredient, recipe);

	}

}
