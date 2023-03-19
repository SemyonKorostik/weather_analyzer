# Weather Analyzer

## Endpoints

### Get information about the current weather 
* GET method
* url: /api/weather/current

### Get information about the average daily temperature for the specified period

* POST method
* url: /api/weather/average/temp
* body: {
"from": "dd-mm-yyyy",
"to": "dd-mm-yyyy"
}

## Technologies
* Java 11
* Spring Boot 
* Spring Data JPA
* PostgreSQL
* Lombok
* MapStruct
* OkHttp
* Mockito
* JUnit
