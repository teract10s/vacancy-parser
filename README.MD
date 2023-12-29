# **📚Vacancy parser Project**

Welcome to the Vacancy parser project! This project is a Java-based web application built with Spring Boot,
Hibernate, Jsoup and other technologies to create a reliable API for parsing html pages.

## **📝1. Introduction**

This project is an application for parsing jobs from https://jobs.techstars.com/jobs.
It is built using Java with a Spring Boot infrastructure using technologies such as Hibernate for
interaction with the database, Liquibase for the database version, Jsoup for parsing the html page. The user has 2 main
functions:

- obtaining vacancies for a certain job function
- filtering of existing vacancies that are in the database

## **💻2. Technologies Used**

* Java 17
* Spring v3.1.4 (Boot, Web, JPA, Security)
* Hibernate
* Swagger
* Mysql v8.0.33
* Mapstruct
* Jsoup v1.15.3

## **📃3. API Documentation**

### **Authentication Controller:**

| **HTTP method** |      **Endpoint**       | **Function**                                     |
|:---------------:|:-----------------------:|:-------------------------------------------------|
|       GET       |    `/api/vacancies`     | Receiving vacancies by specific job function     |
|       GET       | `/api/vacancies/search` | Find specific jobs in the database by parameters |

## **🏁4. Getting Started**

If you want locally run this project follow these steps:

- Clone the repository from GitHub: `git clone https://github.com/teract10s/vacancy-parser.git`
- Create a .env file and fill it with the necessary variables (an example of all the necessary variables in the
  .env.sample file)
- Run project
- The project will be launched at the following link: http://localhost:8080
- Also you can use swagger for testing at the following link: http://localhost:8080/swagger-ui/index.html#
