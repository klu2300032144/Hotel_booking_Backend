# Hotel Booking Backend

A comprehensive Spring Boot backend application for hotel booking management with JWT authentication, MySQL database, and RESTful APIs.

## Features

- **User Authentication**: JWT-based authentication with registration and login
- **Hotel Management**: CRUD operations for hotels with search functionality
- **Room Management**: Room availability, pricing, and booking management
- **Booking System**: Complete booking workflow with date validation and conflict checking
- **Security**: Spring Security with JWT tokens and CORS configuration
- **Database**: MySQL with JPA/Hibernate for data persistence
- **Validation**: Input validation with proper error handling

## Technology Stack

- **Backend**: Spring Boot 3.2.0
- **Database**: MySQL 8.0
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup Instructions

### 1. Database Setup

Create a MySQL database named `hotel_booking_db`:

```sql
CREATE DATABASE hotel_booking_db;
```

### 2. Configuration

Update the database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - User registration
- `POST /api/auth/login` - User login

### Hotels
- `GET /api/hotels` - Get all hotels
- `GET /api/hotels/{id}` - Get hotel by ID
- `POST /api/hotels/search` - Search hotels
- `GET /api/hotels/{id}/rooms` - Get hotel rooms
- `GET /api/hotels/{id}/rooms/available` - Get available rooms

### Rooms
- `GET /api/rooms` - Get all rooms
- `GET /api/rooms/{id}` - Get room by ID
- `GET /api/rooms/hotel/{hotelId}` - Get rooms by hotel
- `GET /api/rooms/hotel/{hotelId}/available` - Get available rooms
- `GET /api/rooms/hotel/{hotelId}/available/dates` - Get available rooms by date range

### Bookings
- `GET /api/bookings` - Get all bookings (authenticated)
- `GET /api/bookings/{id}` - Get booking by ID
- `GET /api/bookings/my-bookings` - Get current user's bookings
- `POST /api/bookings` - Create new booking
- `PUT /api/bookings/{id}/status` - Update booking status
- `PUT /api/bookings/{id}/cancel` - Cancel booking

## Sample Data

The application will automatically create sample data on first run. You can also manually insert sample data using the provided SQL scripts.

## Frontend Integration

This backend is designed to work with a React frontend running on `http://localhost:5173`. CORS is configured to allow requests from this origin.

## Security

- JWT tokens are used for authentication
- Passwords are encrypted using BCrypt
- CORS is configured for frontend integration
- Input validation is implemented for all endpoints

## Development

### Project Structure

```
src/main/java/com/hotelbooking/
├── config/          # Security and configuration classes
├── controller/      # REST controllers
├── dto/            # Data transfer objects
├── entity/         # JPA entities
├── exception/      # Exception handling
├── repository/     # Data access layer
└── service/        # Business logic layer
```

### Adding New Features

1. Create entity classes in the `entity` package
2. Create repository interfaces in the `repository` package
3. Implement business logic in the `service` package
4. Create REST endpoints in the `controller` package
5. Add DTOs for request/response handling

## License

This project is licensed under the MIT License.
"# Hotel_booking_Backend" 
