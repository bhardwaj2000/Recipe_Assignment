package com.recipe.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;


/**
 * {@link RecipeService} this interface is used for handle repository services
 * this interface implements in {@link RecipeServiceImpl}
 */
@Service
public interface RecipeService {

	public List<Recipe> getAllRecipe();

	public Recipe getRecipe(int id) ;

	public List<Recipe> getVegRecipe(String veg);

	public List<Recipe> getServeAndIngerdient(int serve, String ingredent);

	public List<Recipe> getRecipeByInstructionNotIngredient(String ingre, String instru);

	public Recipe addRecipe(Recipe recipe);

	public Recipe updateRecipe(Recipe recipe, int id);

	public void deleteRecipe(int id);


}