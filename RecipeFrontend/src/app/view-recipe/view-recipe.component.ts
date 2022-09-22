import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, OperatorFunction, window } from 'rxjs';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-view-recipe',
  templateUrl: './view-recipe.component.html',
  styleUrls: ['./view-recipe.component.css']
})
export class ViewRecipeComponent implements OnInit {

  searchText?: any;
  recipe?: Recipe[];
  ids?:any[]=[];
  constructor(private recipeService: RecipeService, private route: Router) { }

  ngOnInit(): void {
    this.getAllRecipe();
  }

  getAllRecipe() {
    this.recipeService.getAllRecipes().subscribe(
      data => {
        console.log(data);
        this.recipe = data;
        for (let index = 0; index < this.recipe.length; index++) {
          this.ids?.push(this.recipe[index].recipeId)
        }
      }
    );
    
  }

  updateRecipe(id?: number) {
    console.log("id :" + id);
    this.recipeService.getRecipeId(id);
    this.route.navigate(['/update-recipe'])
  }
  deleteRecipe(id?: number) {
    console.log("deleted recipe id : " + id);
    this.recipeService.deleteRecipeById(id).subscribe();
    this.route.navigate(['/view-recipe']);
    location.reload();
  }

}


