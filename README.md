# integration-movie-database-api

# About 

A api that integrates to the movie database api(https://www.themoviedb.org/).

# Api Integration Features

The main features of the api:

* Get all genres:  `GET/v1/genres`
* Search Movie By Query: `POST/v1/movies`
* Get movie detail by id: `GET/v1/movies/{movieId}`

# Step to run tests

```bash
mvn test
```

# Step to run the api

```bash
mvn spring-boot:run
```
# In case you want to run with docker

```bash
 docker build -t {image_name} . && docker run -p 8080:8080 --name {container-name} {builded_image_name} .

 #Example:  docker build -t guilhermeneres/api-integration-moviedb . && docker run -p 8080:8080 --name api-moviedb guilhermeneres/api-integration-moviedb .
```

### After run the api, swagger docs is available at (http://localhost:8080/swagger-ui.html#/)


### This springboot api is also available at (https://api-integration-moviedb.herokuapp.com/swagger-ui.html#/)

### The base architecture

```bash
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── br/
    │   │   │       └── com/
    │   │   │           └── dev/
    │   │   │               └── desafioclickbus/
    │   │   │                   ├── ApiDesafioClickbusApplication.java
    │   │   │                   ├── genre/
    │   │   │                   │   ├── controller/
    │   │   │                   │   │   └── GenreController.java
    │   │   │                   │   ├── dto/
    │   │   │                   │   │   ├── GenreDTO.java
    │   │   │                   │   │   └── GenreListDTO.java
    │   │   │                   │   ├── exceptions/
    │   │   │                   │   │   └── GenreIntegrationException.java
    │   │   │                   │   └── service/
    │   │   │                   │       └── GenreService.java
    │   │   │                   ├── movie/
    │   │   │                   │   ├── controller/
    │   │   │                   │   │   └── MovieController.java
    │   │   │                   │   ├── dto/
    │   │   │                   │   │   ├── MovieDetailDTO.java
    │   │   │                   │   │   ├── MovieDTO.java
    │   │   │                   │   │   └── PageDTO.java
    │   │   │                   │   ├── exceptions/
    │   │   │                   │   │   ├── SearchMovieDetailIntegrationException.java
    │   │   │                   │   │   └── SearchMovieIntegrationException.java
    │   │   │                   │   ├── model/
    │   │   │                   │   │   └── MovieSearchRequestForm.java
    │   │   │                   │   └── service/
    │   │   │                   │       └── MovieIntegrationService.java
    │   │   │                   └── shared/
    │   │   │                       ├── configuration/
    │   │   │                       │   └── SpringFoxSwaggerConfig.java
    │   │   │                       └── service/
    │   │   │                           └── BaseIntegrationService.java
    │   │   └── resources/
    │   │       ├── application.properties
    │   │       ├── static/
    │   │       └── templates/
    │   └── test/
    │       └── java/
    │           └── br/
    │               └── com/
    │                   └── dev/
    │                       └── desafioclickbus/
    │                           ├── ApiDesafioClickbusApplicationTests.java
    │                           ├── genre/
    │                           │   └── GenreControllerTest.java
    │                           ├── movie/
    │                           │   └── MovieControllerTest.java
    │                           └── util/
    │                               └── TestUtil.java

```