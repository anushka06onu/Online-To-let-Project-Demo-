# StaySquare - Online To-let System

A modern, student-friendly, and professional web application for finding and listing flats.

## Features

- **Perfect Professional UI**: Redesigned with a modern glassmorphism aesthetic, vibrant colors, and clean typography.
- **Student Friendly**: Fun and accessible design with clear navigation.
- **Light & Dark Mode**: Persistent theme toggle in the header.
- **Full System Fixed**: Resolved compilation errors with Java 25 and database conflict issues.
- **H2 Database Support**: Configured with H2 in-memory database for easy local testing without setting up MySQL.

## Tech Stack

- **Backend**: Java 21+ (Spring Boot 3.3.5)
- **Frontend**: HTML5, Vanilla CSS, Thymeleaf, Bootstrap 5
- **Database**: H2 (In-memory for Dev) / MySQL (Production ready)

## How to Run

1. Ensure you have Java 21 or higher installed.
2. Navigate to the project directory:
   ```bash
   cd onlineToletSystemDemo/onlineToletSystemDemo
   ```
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:8081`.

## Database Configuration

By default, the application is set to use **H2 Database** for easy setup and testing. 

If you want to switch back to **MySQL** for production or persistent storage, update the `src/main/resources/application.properties` file:

```properties
# Uncomment for MySQL
# spring.datasource.url=jdbc:mysql://localhost:3306/online_to_let_system
# spring.datasource.username=root
# spring.datasource.password=your_password
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Comment out H2 settings
spring.datasource.url=jdbc:h2:mem:testdb
...
```

## Fixes Applied

- Removed Lombok dependency and replaced it with explicit getters, setters, and constructors to fix compilation errors with Java 25.
- Renamed the `User` table to `users` to avoid conflicts with H2 reserved keywords.
- Changed default port to `8081` to avoid conflicts with other services running on `8080`.
- Refactored HTML templates to use Thymeleaf fragments for header and head, making the code cleaner and more maintainable.
