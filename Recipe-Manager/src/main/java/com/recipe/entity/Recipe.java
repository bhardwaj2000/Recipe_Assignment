package com.recipe.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recipe {

	@Id
	private int recipeId;
	private String veg;
	private int serve;
	private String ingredent;
	private String instruction;

	public Recipe() {
		// TODO Auto-generated constructor stub
	}

	public Recipe(int recipeId, String veg, int serve, String ingredent, String instruction) {
		super();
		this.recipeId = recipeId;
		this.veg = veg;
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

	public String getVeg() {
		return veg;
	}

	public void setVeg(String veg) {
		this.veg = veg;
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
		return "Recipe [recipeId=" + recipeId + ", veg=" + veg + ", serve=" + serve + ", ingredent=" + ingredent
				+ ", instruction=" + instruction + "]";
	}

}