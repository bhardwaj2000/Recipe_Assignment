package com.recipe.repository;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipe.entity.Recipe;

/**
 * {@link RecipeRepository} this class to handle custom and general
 * {@link JpaRepository} here custom findByInstructionAndNotByIngredent method
 * is used here use {@link NativeQuery} for MySql database
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

	// find List of type Recipe
	List<Recipe> findByType(String type);

	// find List of recipe by serve and ingredient
	List<Recipe> findByServeAndIngredent(int serve, String ingredent);

	// find List of recipe by which have given instruction, but not ingredient
	@Query(value = "select * from recipe where ingredent!=?1 and instruction=?2", nativeQuery = true)
	List<Recipe> findByInstructionAndNotByIngredent(String ingredient, String instruction);

}
