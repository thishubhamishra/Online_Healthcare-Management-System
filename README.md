# Online Healthcare Management System

## 1. Overview
A Java web-based system that allows:
- Patient registration & login
- Appointment booking with doctors
- Viewing and cancelling appointments
- Basic dashboard for logged-in patients

## 2. Tech Stack
- Java Servlets, JSP
- JDBC, MySQL
- HTML, CSS, JavaScript (client-side validation)
- JUnit (for DAO testing)

## 3. Core Features Implemented
- Full flow: Register → Login → Dashboard → Book Appointment → View/Cancel
- Data validation:
  - Client-side: JavaScript form validation (password checks, date checks)
  - Server-side: validation in Servlets and DAO (email format, password length, date in future)
- Error handling:
  - Try/catch around DB calls
  - Central `error.jsp` to show friendly error messages
- Integration:
  - DAO layer + Servlets + JSP pages + MySQL schema
- Innovation / Extra Effort:
  - Time-slot conflict check to prevent double booking
  - Simple dashboard page for logged-in user

## 4. Database Setup
- Import and run `schema.sql` in MySQL.
- Update DB username/password in `DBUtil.java`.

## 5. How to Run
- Import the project into Eclipse.
- Add MySQL JDBC Driver to the Build Path.
- Import schema.sql into MySQL.
- Configure DBUtil with your MySQL username and password.
- Start MySQL Server.
- Run the project on Apache Tomcat.

## 6. Team Members
- Shubham Mishra - Backend development, DAO layer, Servlets, DB integration
- Aditya Sharma -	JSP pages, UI design, validation
- Raushan Roy - Testing, error handling, documentation
- Raushan Tiwari - Appointment module, dashboard, enhancements

## 7. Conclusion
- Core functionality
- Robust backend integration
- Validation and error handling
- Clean MVC architecture
- Testing included
- Strong documentation
- Team collaboration
- Innovation features

## License
This project is for educational purposes and open-source. You are free to modify and redistribute it with attribution.

