import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from './recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipeId?: number;
  recipeUrl = "http://localhost:3050/recipes";
  constructor(private http: HttpClient) { }

  getRecipeId(id?: number) {
    this.recipeId = id;
  }

  // Fetch all recipe
  // http://localhost:3050/recipes
  getAllRecipes(): Observable<Recipe[]> {

    return this.http.get<Recipe[]>(`${this.recipeUrl}`);
  }

  // add new recipe , post method
  // http://localhost:3050/recipes 
  addRecipes(recipe?: Recipe): Observable<object> {
    return this.http.post<object>(`${this.recipeUrl}`, recipe);
  }

  // Fetch recipe by recipeId
  // http://localhost:3050/recipes/1
  getRecipeById(): Observable<object> {
    return this.http.get<object>(`${this.recipeUrl}/${this.recipeId}`);
  }

  // Fetch recipe by recipeId
  // http://localhost:3050/recipes/1
  getUniqueRecipeById(id?: number): Observable<object> {
    return this.http.get<object>(`${this.recipeUrl}/${id}`);
  }

  // http://localhost:3050/recipes/veg?veg=veg
  getVegRecipes(veg?: string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/veg?veg=${veg}`);
  }

  //http://localhost:3050/recipes/?serve=5&ingredient=soleman
  getRecipeByServeIngredient(serve?: number, ingre?: string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.recipeUrl}/?serve=${serve}&ingredient=${ingre}`);
  }

  // update existing recipe by recipeId, put method
  // http://localhost:3050/recipes/1
  updateRecipeById(recipe?: Recipe): Observable<object> {
    return this.http.put<object>(`${this.recipeUrl}/${this.recipeId}`, recipe);
  }


  // delete recipe by recipeId, delete method
  // http://localhost:3050/recipes/1
  deleteRecipeById(id?: number): Observable<object> {
    return this.http.delete<object>(`${this.recipeUrl}/${id}`);
  }

}
