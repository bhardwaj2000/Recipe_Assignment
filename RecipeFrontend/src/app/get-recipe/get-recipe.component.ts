import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-get-recipe',
  templateUrl: './get-recipe.component.html',
  styleUrls: ['./get-recipe.component.css']
})
export class GetRecipeComponent implements OnInit {

  recipe: Recipe = new Recipe();
  uniqueRecipe?: any;
  serverecipe?: Recipe[];
  vegrecipe?: Recipe[];
  show: boolean = false;
  showveg: boolean = false;
  getItem = new FormGroup({
    recipeId: new FormControl('', Validators.required)
  })
  getserveItem = new FormGroup({
    serve: new FormControl('', Validators.required),
    ingred: new FormControl('', Validators.required)
  })
  getvegItem = new FormGroup({
    veg: new FormControl('', Validators.required)
  })
  constructor(private recipeService: RecipeService, private route: Router) { }

  ngOnInit(): void {
  }

  get recipeId() {
    return this.getItem.get('recipeId');
  }
  get serve() {
    return this.getserveItem.get('serve');
  }
  get ingred() {
    return this.getserveItem.get('ingred');
  }
  get veg() {
    return this.getvegItem.get('veg');
  }

  getRecipeById(id?: number) {
    this.recipeService.getUniqueRecipeById(id).subscribe(
      data => {
        console.log("data :" + data);
        this.uniqueRecipe = data;
     //   console.log("ids :"+ids)
      })
  }

  getVegRecipe(veg?: string) {
    this.recipeService.getVegRecipes(veg).subscribe(
      data => {
        if (data.length > 0) {
          this.showveg = true;
          this.vegrecipe = data;
        } else {
          this.showveg = false;
        }
      })
  }

  getRecipeByServeAndIngredient(serve?: number, ingred?: string) {
    this.recipeService.getRecipeByServeIngredient(serve, ingred).subscribe(
      data => {
        console.log(data);
        if (data.length > 0) {
          this.show = true;
          this.serverecipe = data;
        } else {
          this.show = false;
        }
      })
  }



  isJson(str: any) {
    try {
      JSON.parse(str);
    } catch (e) {
      return false;
    }
    return true;
  }

}
