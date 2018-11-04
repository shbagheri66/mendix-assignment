This project is a solution for Mendix assignment and has been implemented using :
-	Java
-	Spring-boot
- JUnit
- JAXB
- Maven
- Gson

It contains some REST services to manage recipes.

## How to run?

 move to project location in command line,then enter below command

> mvnw spring-boot:run

## How to run tests?

in the project base folder, enter the below command
> mvnw surefire:test

## How to create a runnable JAR?

 enter below command

> mvnw package

then go to target directory,then enter the following command

> java -jar assignment-x.x.x-SNAPSHOT.jar

## Default port

the application will run on port 8081 by default, you can change in application.properties file.

## REST API Addresses

get all recipes :
> GET http://localhost:8081/public/recipe/recipedto

get recipes that are in special category:
> GET http://localhost:8081/public/recipe/recipedto/{category name}

get recipes that have special text in title or category(quick search):
> GET http://localhost:8081/public/recipe/search/{search string}

add new recipe
> POST http://localhost:8081/public/recipe/add

get all categories 
> GET http://localhost:8081/public/category
