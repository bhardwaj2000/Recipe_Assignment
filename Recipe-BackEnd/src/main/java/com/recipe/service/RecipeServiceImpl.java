package com.recipe.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipe.controller.RecipeController;
import com.recipe.entity.Recipe;
import com.recipe.exception.ListEmptyException;
import com.recipe.exception.RecipeNotFoundException;
import com.recipe.repository.RecipeRepository;

/**
 * {@link RecipeServiceImpl} this class implements {@link RecipeService}
 * this class get value from repository and pass value to {@link RecipeController} class
 */
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	private static Logger logger=LoggerFactory.getLogger(RecipeServiceImpl.class);

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	// service for get all recipe
	@Override
	public List<Recipe> getAllRecipe(){
		logger.info("InSide class RecipeService!!! Method  getRecipeById!!!");
		try {
			List<Recipe> allRecipe = recipeRepository.findAll();
			if(allRecipe.isEmpty()) {
				throw new ListEmptyException(" Recipe DB is empty");
			}
			return allRecipe;
		} catch (Exception e) {
			throw new ListEmptyException(e.getMessage());
		}				
	}

	// service for get recipe by id or get unique recipe
	@Override
	public Recipe getRecipe(int id) {
		logger.info("InSide class RecipeService!!! Method getRecipeById!!!");
		return this.recipeRepository.findById(id).orElseThrow(
				()-> new RecipeNotFoundException("No Recipe found with given Id : "+id));

	}

	// service for get all veg recipe
	@Override
	public List<Recipe> getVegRecipe(String veg) {
		logger.info("InSide class RecipeService!!! Method  getVegRecipe!!!");
		try {
			List<Recipe> vegRecipe = recipeRepository.findByType(veg);
			if(vegRecipe.isEmpty()) {
				throw new ListEmptyException(String.format("No List of %s type Recipe in DB", veg));
			}
			return vegRecipe;
		} catch (Exception e) {
			throw new ListEmptyException( e.getMessage());
		}
	}

	// service for get recipe by serve and ingredient
	@Override
	public List<Recipe> getServeAndIngerdient (int serve, String ingredent) {
		logger.info("InSide class RecipeService!!! Method getServeAndIngerdient!!!");
		List<Recipe> matchRecipe = null;
		try {
			matchRecipe = recipeRepository.findByServeAndIngredent(serve, ingredent);
			if(matchRecipe.isEmpty()) {
				throw new ListEmptyException(String.format("No List with %d serve and %s ingredient in DB.",serve,ingredent));
			}
			return matchRecipe;
		} catch (Exception e) {
			throw new ListEmptyException(e.getMessage());
		}
		
	}

	// service for get recipe by instruction not ingredient
	@Override
	public List<Recipe> getRecipeByInstructionNotIngredient(String ingre, String instru) {
		logger.info("InSide class RecipeService!!! Method  getRecipeByInstructionNotIngredient!!!");
		List<Recipe> matchRecipe = null;
		try {
			matchRecipe = recipeRepository.findByInstructionAndNotByIngredent(ingre, instru);
			if(matchRecipe.isEmpty()) {
				throw new ListEmptyException(String.format("No List with %s instruction not %s ingredient in DB.",instru,ingre));
			}
			return matchRecipe;
		} catch (Exception e) {
			throw new ListEmptyException(e.getMessage());
		}
	}

	// service for add recipe
	@Override
	public Recipe addRecipe( Recipe recipe) {
		logger.info("InSide class RecipeService!!! Method  AddRecipe!!!");
		Recipe recipe2 =recipeRepository.save(recipe);
		return  recipe2;
	}

	// service for update recipe
	@Override
	public Recipe updateRecipe(Recipe recipe, int id) {
		logger.info("InSide class RecipeService!!! Method  DeleteRecipe!!!");
		Recipe recipe2=this.recipeRepository.findById(id).orElseThrow(
				()-> new RecipeNotFoundException(String.format("No Recipe with given id : %d found in DB for Update", id)));
		recipe2.setRecipeId(id);
		recipe2.settype(recipe.gettype());
		recipe2.setServe(recipe.getServe());
		recipe2.setInstruction(recipe.getInstruction());
		recipe2.setIngredent(recipe.getIngredent());
		this.recipeRepository.save(recipe2);
		return recipe2;
	}

	// service for delete recipe
	@Override
	public void deleteRecipe(int id) {
		
		logger.info("InSide class RecipeService!!! Method  DeleteRecipe!!!");
		Recipe recipe=this.recipeRepository.findById(id).orElseThrow(
				()-> new RecipeNotFoundException(String.format("No Recipe with given id : %d found in DB For delete",id)));
		this.recipeRepository.delete(recipe);

	}

}