# Overview

As a software engineer, I’m constantly looking to improve my understanding of new languages, frameworks, and design patterns. In this project, I built a RESTful API using Java and Spring Boot to reinforce my understanding of object-oriented programming, REST principles, and the structure of Java-based applications.

The software is a Task Management API that allows users to create, read, update, delete, and export tasks. Each task includes fields such as title, description, due date, and priority. The API also includes filtering capabilities and demonstrates file I/O, interface usage, and integration with Java collections.

This project allowed me to explore Spring Boot's annotation-based approach, dependency injection, HTTP request mapping, and how to manage state with in-memory data using JpaRepository.
[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

IDE: IntelliJ IDEA

Language: Java 21

Framework: Spring Boot

Dependencies: Spring Web, Spring Data JPA, H2 Database

Build Tool: Maven

# Useful Websites


- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/)
- [Create your FIRST CRUD RESTful API ](https://www.youtube.com/watch?v=YVl6M5ztOu8)
- [Spring Data JPA and H2 Setup ](https://www.youtube.com/watch?v=YD_ga0r87wU)

# Future Work


- Add persistent database support (e.g., PostgreSQL)
- Improve error handling with custom exception classes
- Add unit tests for each controller method
- Implement user authentication for task ownership
- Allow task sorting and pagination in /api/tasks endpoint