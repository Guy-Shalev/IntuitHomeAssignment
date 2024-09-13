# Player Data API

[![Java](https://img.shields.io/badge/Java-11%2B-blue.svg)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A Spring Boot application providing an API for accessing player data.

## 📋 Table of Contents

- [Features](#-features)
- [Technologies](#-technologies)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation-and-running-without-docker)
  - [Docker](#-docker-optional)
- [Usage](#-usage)
- [API Endpoints](#-api-endpoints)
- [Testing](#-testing)
- [Logging](#-logging)
- [Database](#-database)
- [Future Improvements](#-future-improvements)
- [License](#-license)

## ✨ Features

- Retrieve a paginated list of all players
- Get detailed information about a specific player by ID
- Data loaded from a CSV file on application startup
- Error handling for player not found scenarios

## 🛠 Technologies

- Java 11
- Spring Boot 2.x
- Spring Data JPA
- H2 Database (in-memory)
- JUnit 5
- Log4j2
- Lombok

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Docker (optional, for containerized deployment)

### Installation and Running (Without Docker)

1. Clone the repository:
   ```sh
   git clone https://github.com/Guy-Shalev/IntuitHomeAssignment.git
   ```

2. Navigate to the project directory:
   ```sh
   cd IntuitHomeAssignment
   ```

3. Build the project:
   ```sh
   mvn clean install
   ```

4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

Note: This method runs the application directly on your machine without using Docker.

## 🐳 Docker (Optional)

If you prefer to run the application in a containerized environment, you can use Docker. This is optional and not required to run the application.

### Building the Docker Image

To build a Docker image for this application:

1. Ensure you are in the root directory of the project (where the Dockerfile is located).
2. Run the following command:

   ```bash
   docker build -t intuit-home-assignment .
   ```
This command builds a Docker image and tags it as intuit-home-assignment.

### Running the Docker Container

To run the application in a Docker container:

   ```bash
   docker run -p 8080:8080 intuit-home-assignment
   ```
This command starts a container from the intuit-home-assignment image and maps port 8080 of the container to port 8080 on your host machine.

You can now access the application at http://localhost:8080, just as you would when running it without Docker.

### Stopping the Docker Container

To stop the running container:

1. Find the container ID:
   ```bash
   docker ps
   ```

2. Stop the container:
   ```bash
   docker stop [CONTAINER_ID]
   ```

## 🖥 Usage

Whether you're running the application directly on your machine or using Docker, once the application is running, you can use tools like cURL, Postman, or any HTTP client to interact with the API endpoints at http://localhost:8080.
## 📡 API Endpoints

### GET /api/players

Retrieve a list of players. This endpoint supports pagination and sorting.

The API can be used in the following ways:

* Without parameters (returns all players): 
  ```
  GET /api/players
  ```

* With pagination:
  ```
  GET /api/players?page=2&size=10
  ```
  - `page`: Page number (default: 0)
  - `size`: Number of items per page (default: 20)

* With sorting:
  ```
  GET /api/players?sort=nameFirst,asc
  ```
  - `sort`: Field to sort by, followed by sort direction (`asc` or `desc`)

* With pagination and sorting:
  ```
  GET /api/players?page=1&size=15&sort=nameLast,desc
  ```

Examples:
- Retrieve the first 20 players: `GET http://localhost:8080/api/players`
- Retrieve the second page of 10 players: `GET http://localhost:8080/api/players?page=1&size=10`
- Retrieve players sorted by first name in ascending order: `GET http://localhost:8080/api/players?sort=nameFirst,asc`
- Retrieve the second page of 15 players, sorted by last name in descending order: `GET http://localhost:8080/api/players?page=1&size=15&sort=nameLast,desc`

### GET /api/players/{playerID}

Retrieve details of a specific player.

Example: `GET http://localhost:8080/api/players/johnsm01`

Response: Returns detailed information about the player with ID 'johnsm01'.

## 🧪 Testing

To run the tests, make sure you are in the correct directory:

1. Navigate to the project root directory:
    ```sh
    cd ~\IntuitHomeAssignment\IntuitHomeAssignment
    ```
2. Run the tests:
    ```sh
    mvn test
    ```

## 📝 Logging

- Logs are written to both the console and a file located at `logs/app.log`.
- The logging level for the application package is set to DEBUG.

## 💾 Database

- The application uses an H2 in-memory database.
- H2 console is enabled and can be accessed at `http://localhost:8080/h2-console` when the application is running.
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## 🔮 Future Improvements

Here are some potential improvements that could be made to enhance the application:

1. Improved Logging: Enhance logging throughout the application to aid in debugging and monitoring.
2. Basic Search Functionality: Implement a simple search endpoint that allows searching players by name or birth year.
3. Basic Caching: Implement simple in-memory caching for frequently accessed data using Spring's @Cacheable annotation.
4. API Documentation: Add Swagger/OpenAPI documentation to make the API more user-friendly for developers.
5. Unit Test Coverage: Increase unit test coverage, particularly for edge cases and error scenarios.
6. Performance Profiling: Use tools like VisualVM or JProfiler to identify and address any performance bottlenecks.
7. Data Validation: Implement more robust data validation when loading data from the CSV file.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

For any additional questions or concerns, please open an issue or contact the repository maintainers.
