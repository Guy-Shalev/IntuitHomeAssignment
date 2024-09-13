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
  - [Installation](#installation)
- [Usage](#-usage)
- [API Endpoints](#-api-endpoints)
- [Testing](#-testing)
- [Logging](#-logging)
- [Database](#-database)
- [Contributing](#-contributing)
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

### Installation

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

## 🖥 Usage

Once the application is running, you can use tools like cURL, Postman, or any HTTP client to interact with the API endpoints.

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

To run the tests, execute:

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

## 🤝 Contributing

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

For any additional questions or concerns, please open an issue or contact the repository maintainers.
