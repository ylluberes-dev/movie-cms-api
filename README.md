## **Description:**

Restful api built with Java 8, Spring Boot 2.4.0 and h2 in-memory database, this api support the following features:

- Add a new Movie
- Remove a existing movie
- Update the movie details
- Retrieve all movies  
- Like a movie
- Rent a movie
- Sell a movie
- Check the transactions details of a particular movie

## Requirements:

- JDK 1.8*

- Maven 3.x

## Clone repository:

- git clone https://github.com/ylluberes-dev/movie-cms-api.git

## Installation and running:

1) mvn clean
2) mvn install
3) mvn spring-boot:run

## Some test endpoints

-  **Add a new movie**



        curl --request POST \
            --url http://localhost:8000/movies \
            --header 'Content-Type: application/json' \
            --data '{
            "title": "Sipderman",
            "description": "Another marvel movie",
            "stock": 5,
            "rentalPrice": 50,
            "salePrice": 100,
            "available":true
            }':


- **Remove a existing move**



        curl --request DELETE \
          --url http://localhost:8000/movies/1
        

- **Like a movie**


        curl --request POST \
        --url http://localhost:8000/likes \
        --header 'Content-Type: application/json' \
        --data '{
        "movieId": 1,
        "customerEmail": "customer@gmail.com"
        }':

- **Rent a movie**


        curl --request POST \
        --url http://localhost:8000/rentals \
        --header 'Content-Type: application/json' \
        --data '{
        "movieId": 1,
        "customerEmail": "customer@gmail.com"
        }':

- **Sell a movie**


        curl --request POST \
        --url http://localhost:8000/sales \
        --header 'Content-Type: application/json' \
        --data '{
        "movieId": 1,
        "customerEmail": "customer@gmail.com"
        }':


- **NOTE**


This api were built based on requirements of hackerrank platform test.

