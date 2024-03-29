# TQS Individual Project

This report presents the midterm individual project required for TQS, covering both the software product features and the adopted quality assurance strategy.

The objective of this project was to create a **REST-API service**, along with the implementation of tests to verify if everything in the application is working properly. These tests included:
* Unit tests.
* Unit tests with dependency isolation using mocks.
* Integration tests on API.
* Functional tests.

## Course
This project was developed under the [Software Quality And Tests](https://www.ua.pt/en/uc/8109) course of [University of Aveiro](https://www.ua.pt/).

## AirQuality

![example workflow](https://github.com/eduardosantoshf/TQSIndividualProject/actions/workflows/main.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eduardosantoshf_AirQuality&metric=alert_status)](https://sonarcloud.io/dashboard?id=eduardosantoshf_AirQuality)

<p align="center">
    <img style="height: 30px" src="./images/AirQuality_logo.png">
</p>

The AirQuality product allows its user to get air quality data from a specific city, showing, for instance, the AQI (Air Quality Index) and some concentration levels (CO, O3, SO2, NO2), as well as the predominant pollen type of that specific city.

The data regarding the air quality for each city is gathered from the [WeatherBit's Air Quality](https://www.weatherbit.io/api/airquality-current) external API, and can be searched on the products’ webpage, either by the city name or by the latitude and longitude.

There was also implemented a local cache, making repeated calls being stored for 1 minute so that the retrieval of information is faster.

## Technologies / Frameworks

The following technologies / frameworks were used on this project:
* **Spring Boot**, a module of **Spring Framework** - Backend.
* **HTML**, **CSS** and **JS** - Frontend.
* **jQuery's AJAX** - API requests between frontend and backend.
* **Swagger2** - API documentation.
* **JUnit 5** - Unit tests.
* **Mockito** - Unit tests with dependency isolation using mocks.
* **Selenium Web Driver** - Functional tests.
* **Spring Boot MockMVC** - Integration tests.

## Running the Application

AirQuality can be locally deployed. That can be done by running the following command on the repository’s root directory: 

```shell
docker-compose up
```

When everything is up and running, the website can be accessed at: http://localhost:8000/

## Demo

The demo video is inside the root directory of this repository.

## SonarCloud Dashboard

The SonarCloud dashboard can be accessed at: https://sonarcloud.io/dashboard?id=eduardosantoshf_AirQuality

## System Architecture
<p align="center">
    <img style="height: 300px" src="./images/AirQuality_architecture.png">
</p>

## UML Classes Diagram
<p align="center">
    <img style="height: 500px" src="./images/UML_classes_diagram.png">
</p>

## Grade 
This project's grade was **18** out of 20.

## Authors
* **Eduardo Santos**: [eduardosantoshf](https://github.com/eduardosantoshf)
