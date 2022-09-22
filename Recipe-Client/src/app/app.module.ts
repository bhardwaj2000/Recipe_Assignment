import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ViewRecipeComponent } from './view-recipe/view-recipe.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { UpdateRecipeComponent } from './update-recipe/update-recipe.component';
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule, FormsModule } from "@angular/forms";
import { NoPageComponent } from './no-page/no-page.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { GetRecipeComponent } from './get-recipe/get-recipe.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewRecipeComponent,
    AddRecipeComponent,
    UpdateRecipeComponent,
    NoPageComponent,
    GetRecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
