# Sked
**Sked - Scheduler WebApp**

Hey guys! Right now Sked is a RESTful API for a scheduler application built with Java, Spring Framework, Spring REST, and MySQL for persistence. It provides endpoints to manage establishments, shifts, and employees. However, I'll make it a full-stack Web Application using React. 

**Features**
- Establishments: Create, read, update, and delete establishments.
- Shifts: Manage shifts for establishments including entry and leave hours.
- Employees: Handle employee data including name, department, and assigned shift.

# Technologies Used
- Java
- Spring Framework
- Spring REST
- MySQL
- Mockito
- JUnit
- Postman (for API testing)
- React (planned for frontend)

# Usage
**Setup**

- Clone the repository
- Install dependencies
- Configure MySQL database:
    Create a MySQL database named sked.
    Update application.properties with your MySQL database credentials.

**Running the Application**
- Deploy the application to Tomcat using Maven:
- mvn tomcat7:deploy
- The API will be accessible at http://localhost:8080/sked.

**Tests**
Unit tests have been implemented for the services using Mockito and JUnit.

**Endpoints**
- The following endpoints are available:

Establishments:

- GET /api/establishment Get all establishments
- GET /api/establishment/{id}: Get establishment by ID
- GET /api/establishment/{id}/shift: Get all shifts for the establishment with the specified ID
- GET /api/establishment/{id}/employee: Get all employees associated with the establishment with the specified ID
- POST /api/establishment: Create a new establishment
- POST /api/establishment/{id}/{sid}/employee: Add an employee with shift id = sid establishment with the specified ID
- PUT /api/establishment/{id}: Update establishment
- DELETE /api/establishment/{id}: Delete establishment

Shifts:
- GET /api/shift: Get all shifts
- POST /api/shift: Create a new shift
- PUT /api/shift/{id}: Update shift
- DELETE /api/shift/{id}: Delete shift

Employees:
- GET /api/employee: Get all employees
- GET /api/employee/{id}: Get employee by ID
- GET /api/employee/{id}/shift: Get shift for the employee with the specified ID
- PUT /api/employee/{id}: Update employee
- DELETE /api/employee/{id}: Delete employee

Explore the codebase for more details.

# Next Steps
The next step in the project is to develop the frontend using React.
