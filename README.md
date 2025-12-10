# Online Healthcare Management System - Review 2

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

## 3. Core Features Implemented in Review-2
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
1. Import project into Eclipse as Dynamic Web Project (or similar).
2. Add MySQL JDBC driver to project build path.
3. Configure server (Tomcat).
4. Run on server and open `http://localhost:8080/OnlineHealthcareManagementSystem-Review2`.

## 6. Team Members
- Shubham Mishra
- Aditya Sharma
- Raushan Roy
- Raushan Tiwari

