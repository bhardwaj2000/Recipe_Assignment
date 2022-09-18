import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-update-recipe',
  templateUrl: './update-recipe.component.html',
  styleUrls: ['./update-recipe.component.css']
})
export class UpdateRecipeComponent implements OnInit {

  recipe: Recipe = new Recipe();
  updateItem = new FormGroup({
    recipeId: new FormControl('', Validators.required),
    veg: new FormControl('', Validators.required),
    serve: new FormControl('', Validators.required),
    ingred: new FormControl('', [Validators.required]),
    instruc: new FormControl('', Validators.required)
  })
  constructor(private recipeService: RecipeService, private route: Router) { }

  ngOnInit(): void {
    this.getRecipeForUpdate();
  }

  get recipeId() {
    return this.updateItem.get('recipeId');
  }
  get veg() {
    return this.updateItem.get('veg');
  }
  get serve() {
    return this.updateItem.get('serve');
  }
  get ingred() {
    return this.updateItem.get('ingred');
  }
  get instruc() {
    return this.updateItem.get('instruc');
  }

  getRecipeForUpdate() {
    this.recipeService.getRecipeById().subscribe(
      data => {
        console.log(data);
        this.recipe = data;
      }
    )
  }

  updateRecipe(id?: number) {
    console.log(this.recipe);
    this.recipeService.updateRecipeById(this.recipe).subscribe();
    this.route.navigate(['view-recipe'])
  }
}
