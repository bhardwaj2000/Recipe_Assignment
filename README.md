# Recipe_manager_full
Both Client And Server Side Application

This app features a web page using Angular where we can create and list recipes with CRUD operations. 
The app has a server in Spring Boot using MySql database which provides APIs with get, post, put and delete HTTP methods that the client consumes.

## This Project is based on:
* Maven
* Spring Boot
* Spring Data JPA( Mysql Database)
* Angular
* BootStrap

### DataBase Name : **recipe**

## WorkFlow of Spring Boot application:
``` controller -> service -> repository -> entity ```

## All Necessary dependency have been added to "Pom.xml"

* spring web
* Spring DevTool
* Spring Data JPA
* Mysql Driver

## In controller
``` we have made some API, which perform some CURD operations: ```

* Fetch all Recipe: http://localhost:3050/recipes

* Fetch Recipe by RecipeId: http://localhost:3050/recipes/1

<img src="./demo_pic/get1Recipe.png" alt="get recipe by Id" width="80%" height="220px"/>

* Get all Veg Recipe : http://localhost:3050/recipes/veg?veg=veg

* get Recipe by No. of serve and Ingredient: http://localhost:3050/recipes/?serve=5&ingredient=soleman

* get Recipe by Instruction not Ingredient : http://localhost:3050/recipes/soleman/induction

* Add new Recipe :  http://localhost:3050/recipes

<img src="./demo_pic/postRecipe.png" alt="post recipe" width="80%" height="220px"/>

* Update Recipe : http://localhost:3050/recipes/3

<img src="./demo_pic/putRecipe.png" alt="get recipe by Id" width="80%" height="220px"/>

* Delete Recipe : http://localhost:3050/recipes/4

<img src="./demo_pic/deleteRecipe.png" alt="get recipe by Id" width="80%" height="220px"/>

## For Testing :
I have added the Unit Testing For Repository and Service. Integration Testing For Controller.

## In this project we Use Angular as client side application

Here we use spring REST API to fetch all the data from backend and send it to client. For every CURD operations we have make different angular components. There is a service file in which all API has been called and integrate with Front-End. Here we also, add a Routing file in which all the route path has been called. Here is one more component for PageNOTFound if client hit wrong Url.

### Angular application run on port : 3020
``` ng s --port 3020 ```
* for view all Recipe : http://localhost:3020/view-recipe

<img src="./demo_pic/viewRecipeUi.png" alt="get recipe by Id" width="80%" height="220px"/>

* for Add any recipe : http://localhost:3020/add-recipe

<img src="./demo_pic/addRecipe.png" alt="Add recipe by Id" width="80%" height="220px"/>

* for Update-any Recipe: http://localhost:3020/update-recipe

<img src="./demo_pic/upadteRecipe.png" alt="Update recipe by Id" width="80%" height="220px"/>

* for Search any Recipe by RecipeId or serve&Ingredient or vegORNon-veg: http://localhost:3020/get-recipe

<img src="./demo_pic/serchRecipe.png" alt="Search recipe by Id" width="80%" height="220px"/> 

* For Client enter wrong Url : http://localhost:3020/abc

<img src="./demo_pic/pagenotfound.png" alt="page not found" width="80%" height="220px"/>