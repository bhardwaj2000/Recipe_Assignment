package com.recipe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * {@link Recipe} this class used to create database and connect to database
 * here we used the new_recipe database
 * table name here same as class name = recipe
 * here added all the validation to table
 */
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int recipeId;
	@NotNull
	@Size(min=3,message = "Enter veg or non-veg")
	private String type;
	@NotNull
	@Min(value = 1,message = "Enter value of serve with min value 1")
	private int serve;
	@NotNull
	@Size(min=3,message = "Enter Ingredient with minimum length 3")
	private String ingredent;
	@NotNull
	@Size(min=3,message = "Enter Instruction with minimum length 3")
	private String instruction;

	public Recipe() {
	}

	public Recipe(String type, int serve, String ingredent, String instruction) {
		super();
		this.type = type;
		this.serve = serve;
		this.ingredent = ingredent;
		this.instruction = instruction;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public int getServe() {
		return serve;
	}

	public void setServe(int serve) {
		this.serve = serve;
	}

	public String getIngredent() {
		return ingredent;
	}

	public void setIngredent(String ingredent) {
		this.ingredent = ingredent;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", type=" + type + ", serve=" + serve + ", ingredent=" + ingredent
				+ ", instruction=" + instruction + "]";
	}

}