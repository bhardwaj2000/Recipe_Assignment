package com.recipe.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.entity.Recipe;
import com.recipe.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	// service for get all recipe
	@Override
	public List<Recipe> getAllRecipe() {
		return recipeRepository.findAll();
	}

	// service for get recipe by id or get unique recipe
	@Override
	public Recipe getRecipe(int id) {
		Recipe recipe = null;
		try {
			recipe = recipeRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			System.out.println("no element forund");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}

	// service for get all veg recipe
	@Override
	public List<Recipe> getVegRecipe(String veg) {
		List<Recipe> vegRecipe = null;
		try {
			vegRecipe = recipeRepository.findByVeg(veg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vegRecipe;
	}

	// service for get recipe by serve and ingredient
	@Override
	public List<Recipe> getServeAndIngerdient(int serve, String ingredent) {
		List<Recipe> matchRecipe = null;
		try {
			matchRecipe = recipeRepository.findByServeAndIngredent(serve, ingredent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchRecipe;
	}

	// service for get recipe by instruction not ingredient
	@Override
	public List<Recipe> getRecipeByInstructionNotIngredient(String ingre, String instru) {
		List<Recipe> matchRecipe = null;
		try {
			matchRecipe = recipeRepository.findByInstructionAndNotByIngredent(ingre, instru);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchRecipe;
	}

	// service for add recipe
	@Override
	public Recipe addRecipe(Recipe recipe) {
		Recipe recipe2 = recipeRepository.save(recipe);

		return recipe2;
	}

	// service for update recipe
	@Override
	public void updateRecipe(Recipe recipe, int id) {
		recipe.setRecipeId(id);
		recipeRepository.save(recipe);

	}

	// service for delete recipe
	@Override
	public void deleteRecipe(int id) {
		recipeRepository.deleteById(id);

	}

}