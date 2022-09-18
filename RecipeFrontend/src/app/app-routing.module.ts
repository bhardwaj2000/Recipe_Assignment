import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { GetRecipeComponent } from './get-recipe/get-recipe.component';
import { NoPageComponent } from './no-page/no-page.component';
import { UpdateRecipeComponent } from './update-recipe/update-recipe.component';
import { ViewRecipeComponent } from './view-recipe/view-recipe.component';

const routes: Routes = [
  {path:'view-recipe',component:ViewRecipeComponent},
  {path:'add-recipe',component:AddRecipeComponent},
  {path:'update-recipe',component:UpdateRecipeComponent},
  {path:'get-recipe',component:GetRecipeComponent},
  {path:'',redirectTo:"/view-recipe",pathMatch:'full'},
  {path:'**',component:NoPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
