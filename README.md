# Cricbuzz Platform API

Welcome to the Cricbuzz Platform API! This API allows users to browse cricket matches and their details, with role-based access control to manage different types of users (Admin and Guest). Admins can perform all CRUD operations on matches and players, while guests can only view match details.

## Table of Contents

- [Problem Statement](#problem-statement)
- [Technology Stack](#technology-stack)
- [Setup Instructions](#setup-instructions)
- [API Endpoints](#api-endpoints)
- [Assumptions](#assumptions)

## Problem Statement

You are tasked with designing an API for a platform similar to Cricbuzz. Guest users can browse matches and view details, while admins can manage matches, teams, and player statistics. The API should support role-based access control and include the following functionalities:

1. Register Admin
2. Login User
3. Create Match
4. Get Match Schedules
5. Get Match Details
6. Add a Team Member to a Squad
7. Get Player Statistics

## Technology Stack

- **Backend Framework**: Spring Boot
- **Security**: Spring Security
- **API Testing**: Postman
- **Database**: MySQL

## Setup Instructions

### Prerequisites

- Java 11 or higher
- MySQL
- Maven
- Postman (for testing)

### Steps

1. **Clone the repository**:
    ```bash
    git clone https://github.com/glunkad/Cricbuzz.git
    cd Cricbuzz
    ```

2. **Configure MySQL Database**:
    - Create a new MySQL database named `cricbuzz_db`.
    - Update the `application.properties` file in the `src/main/resources` directory with your MySQL database credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/cricbuzz_db
      spring.datasource.username=<your_username>
      spring.datasource.password=<your_password>
      spring.jpa.hibernate.ddl-auto=update
      ```

3. **Build the Project**:
    ```bash
    mvn clean install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

5. **Testing the API**:
    - Use Postman to test the API endpoints. Import the provided Postman collection or create requests based on the API documentation below.

## API Endpoints

### 1. Register Admin
- **Endpoint**: `POST /api/auth/register`
- **Description**: Register a new admin user.
- **Request Body**:
    ```json
    {
        "username": "admin",
        "password": "password"
    }
    ```

### 2. Login User
- **Endpoint**: `POST /api/auth/login`
- **Description**: Log in to get an authorization token.
- **Request Body**:
    ```json
    {
        "username": "admin",
        "password": "password"
    }
    ```

### 3. Create Match
- **Endpoint**: `POST /api/matches`
- **Description**: Create a new match (Admin only).
- **Request Header**: `Authorization: Bearer <token>`
- **Request Body**:
    ```json
    {
      "team1": "South Africa",
      "team2": "New Zealand",
      "venue": "Newlands Cricket Ground",
      "date": "2024-07-10"
    }

    ```

### 4. Get Match Schedules
- **Endpoint**: `GET /api/matches`
- **Description**: Get all match schedules (Guest and Admin).
- **Response**:
    ```json
    [
      {
          "id": 1,
          "venue": "Newlands Cricket Ground",
          "date": "2024-07-10",
          "team1": "South Africa",
          "team2": "New Zealand"
      }
    ]
    ```

### 5. Get Match Details
- **Endpoint**: `GET /api/matches/{id}`
- **Description**: Get details of a particular match (Guest and Admin).
  - **Response**:
      ```json
    {
      "id": 1,
      "venue": "Newlands Cricket Ground",
      "date": "2024-07-10",
      "team1": "South Africa",
      "team2": "New Zealand",
      "squads": [
          {
              "name": "South Africa",
              "playerList": [
                  {
                      "name": "Quinton de Kock",
                      "role": "Batsman"
                  },
                  {
                      "name": "Kagiso Rabada",
                      "role": "Bowler"
                  }
              ]
          },
          {
              "name": "New Zealand",
              "playerList": [
                  {
                      "name": "Kane Williamson",
                      "role": "Batsman"
                  },
                  {
                      "name": "Trent Boult",
                      "role": "Bowler"
                  }
              ]
          }
      ]
    }
    ```

### 6. Add a Team Member to a Squad
- **Endpoint**: `POST /api/teams/{teamId}/players`
- **Description**: Add a player to the team's squad (Admin only).
- **Request Header**: `Authorization: Bearer <token>`
- **Request Body**:
    ```json
    {
        "playerName": "Player 1",
        "role": "Batsman"
    }
    ```

### 7. Get Player Statistics
- **Endpoint**: `GET /api/players/{id}/statistics`
- **Description**: Get statistics of a player (Admin only).
- **Request Header**: `Authorization: Bearer <token>`
- **Response**:
    ```json
    {
        "id": 1,
        "name": "Quinton de Kock",
        "matchesPlayed": 121,
        "runs": 5238,
        "average": 45.66,
        "strikeRate": 90.63,
        "role": null
    }
    ```

## Assumptions

- Dummy data can be used for testing purposes.
- Appropriate error handling should be implemented for robustness.
- Ensure proper validation and authentication for all endpoints.

Thank you for using the Cricbuzz Platform API! If you have any questions or encounter issues, feel free to open an issue on the repository. Happy coding!