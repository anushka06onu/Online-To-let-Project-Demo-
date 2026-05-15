# StaySquare - Online To-Let System

StaySquare is a professional, student-friendly web application designed to help students find suitable accommodation near their campus. It connects students with house owners offering flats for rent.

## Motivation

While there are many platforms for finding apartments or sublets, they often cater to families or individuals looking for entire spaces. Students have unique needs:
- **Seat-Based Search**: Students often share rooms to save costs (3-4 people per room). They need to search for available *seats* within a room rather than renting the whole room or flat.
- **Roommate Matching**: Finding compatible roommates is crucial for students.
- **Student-Specific Filters**: Features like "Female Only", "Short Term" (for internships), and budget-friendly options are central to the platform.

StaySquare is built specifically to address these gaps, making it easier for students to find affordable, shared living spaces with the right filters.

## Features

- **Modern UI**: Glassmorphism aesthetic with support for both Light and Dark modes.
- **Role-Based Access**: Dedicated workflows for Tenants (Students) and House Owners.
- **Flat & Seat Listings**: Browse flats with detailed information, including specific sections for short-term and female-only accommodations.
- **Interactive Design**: Smooth animations, hover effects, and responsive layout.
- **FAQ Section**: Helpful information for users navigating the platform.

## Technologies Used

- **Backend**: Spring Boot 3.3.5, Java 25
- **Frontend**: HTML5, Thymeleaf, CSS3, Bootstrap 5.3
- **Database**: H2 In-Memory Database (for development and testing)
- **Icons**: Font Awesome 6.0

## Getting Started

### Prerequisites

- Java 25
- Maven

### Installation & Running

1. Clone the repository.
2. Navigate to the project directory:
   ```bash
   cd onlineToletSystemDemo/onlineToletSystemDemo
   ```
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application in your browser at `http://localhost:8081`.

## Project Structure

- `src/main/java`: Contains the Java source code (Controllers, Models, Repositories).
- `src/main/resources/templates`: Thymeleaf HTML templates.
- `src/main/resources/static`: Static assets (CSS, images).
- `src/main/resources/data.sql`: Dummy data to populate the database on startup.

## Developed By

**Fateha Hossain Anushka**
