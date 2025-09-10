# HobbyClass Backend Project Documentation

## Project Overview
This backend application serves a hobby class management system where mentors can create and manage classes and experiences, and users can browse and participate in these offerings.

## Project Structure

### 1. Entities

#### User/AppUser
- Represents registered users of the platform
- Fields:
  - id (Long)
  - username (String, unique)
  - password (String, encrypted)
  - name (String)

#### Mentor
- Represents class instructors/mentors
- Fields:
  - id (Long)
  - name (String)
  - expertise (String)
  - bio (String)
  - email (String)
  - phone (String)
  - username (String)
  - password (String, encrypted)

#### HobbyClass
- Represents classes offered by mentors
- Fields:
  - id (Long)
  - title (String)
  - description (String)
  - category (String)
  - schedule (String)
  - price (Double)
  - mentor (Mentor relationship)

#### Experience
- Represents special events or experiences offered
- Fields:
  - id (Long)
  - name (String)
  - details (String)
  - date (LocalDate)
  - duration (Integer, in minutes)
  - mentor (Mentor relationship)

### 2. Controllers & API Endpoints

#### Authentication Controller (`/api/auth`)
- User Login: `POST /api/auth/login/user`
  ```json
  {
    "username": "username",
    "password": "password"
  }
  ```
- Mentor Login: `POST /api/auth/login/mentor`
  ```json
  {
    "username": "mentoruser",
    "password": "mentorpass"
  }
  ```

#### User Controller (`/api/users`)
- Register User: `POST /api/users`
  ```json
  {
    "username": "newuser",
    "password": "password",
    "name": "User Name"
  }
  ```

#### Mentor Controller (`/api/mentors`)
- Create Mentor: `POST /api/mentors`
  ```json
  {
    "name": "Mentor Name",
    "expertise": "Subject",
    "bio": "Mentor Bio",
    "email": "mentor@example.com",
    "phone": "1234567890",
    "username": "mentoruser",
    "password": "mentorpass"
  }
  ```
- Get All Mentors: `GET /api/mentors`
- Get Mentor by ID: `GET /api/mentors/{id}`
- Update Mentor: `PUT /api/mentors/{id}`
- Delete Mentor: `DELETE /api/mentors/{id}`

#### Class Controller (`/api/classes`)
- Create Class: `POST /api/classes`
  ```json
  {
    "title": "Class Title",
    "description": "Class Description",
    "category": "Category",
    "schedule": "Schedule Time",
    "price": 500.0,
    "mentor": {
      "id": 1
    }
  }
  ```
- Get All Classes: `GET /api/classes`
- Get Class by ID: `GET /api/classes/{id}`
- Update Class: `PUT /api/classes/{id}`
- Delete Class: `DELETE /api/classes/{id}`
- Get Classes by Mentor: `GET /api/classes/mentor/{mentorId}`

#### Experience Controller (`/api/experiences`)
- Create Experience: `POST /api/experiences`
  ```json
  {
    "name": "Experience Name",
    "details": "Experience Details",
    "date": "2025-09-15",
    "duration": 90,
    "mentor": {
      "id": 1
    }
  }
  ```
- Get All Experiences: `GET /api/experiences`
- Get Experience by ID: `GET /api/experiences/{id}`
- Update Experience: `PUT /api/experiences/{id}`
- Delete Experience: `DELETE /api/experiences/{id}`
- Get Experiences by Mentor: `GET /api/experiences/mentor/{mentorId}`

### 3. Services

#### AuthService
- Handles user and mentor authentication
- Password encryption using BCrypt
- Login validation

#### MentorService
- Mentor CRUD operations
- Password encryption for mentor accounts
- Mentor data validation

#### HobbyClassService
- Class management operations
- Mentor association with classes
- Class data validation

#### ExperienceService
- Experience management operations
- Mentor association with experiences
- Experience data validation

### 4. Security Configuration

- Password encryption using BCryptPasswordEncoder
- Public endpoints:
  - `/api/auth/**` (login endpoints)
  - `/api/users` (user registration)
  - `/api/mentors/**` (mentor operations)
  - `/api/classes/**` (class operations)
  - `/api/experiences/**` (experience operations)

### 5. Exception Handling
Global exception handling for:
- Invalid request data
- Resource not found
- Authentication failures
- Validation errors

## Testing the API

### Prerequisites
- Java 17
- Maven
- Postman or similar API testing tool

### Important Notes
1. All POST/PUT requests require `Content-Type: application/json` header
2. Passwords are automatically hashed using BCrypt
3. IDs are auto-generated for all entities
4. Dates should be in ISO format (YYYY-MM-DD)

### Common HTTP Status Codes
- 200: Success
- 201: Created
- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 500: Internal Server Error
