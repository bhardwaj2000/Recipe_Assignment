import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  recipe:Recipe=new Recipe();
  addItem= new FormGroup({
    recipeId: new FormControl('',Validators.required),
    veg:new FormControl('',Validators.required),
    serve:new FormControl('',Validators.required),
    ingred:new FormControl('',[Validators.required]),
    instruc:new FormControl('',Validators.required)
  })
  constructor(private recipeService:RecipeService,private route:Router) { }

  ngOnInit(): void {
  }

  get recipeId(){
    return this.addItem.get('recipeId');
  }
  get f(){
    return this.addItem.controls;
  }
  get veg(){
    return this.addItem.get('veg');
  }
  get serve(){
    return this.addItem.get('serve');
  }
  get ingred(){
    return this.addItem.get('ingred');
  }
  get instruc(){
    return this.addItem.get('instruc');
  }
 
  addRecipe(){
    console.log(this.recipe);
    this.recipeService.addRecipes(this.recipe).subscribe();
    this.route.navigate(['view-recipe']);
  }
}
