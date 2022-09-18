package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

	// find List of veg Recipe
	List<Recipe> findByVeg(String veg);

	// find List of recipe by serve and ingredient
	List<Recipe> findByServeAndIngredent(int serve, String ingredent);

	// find List of recipe by which have given instruction, but not ingredient
	@Query(value = "select * from recipe where ingredent!=?1 and instruction=?2", nativeQuery = true)
	List<Recipe> findByInstructionAndNotByIngredent(String ingredient, String instruction);

}
