<%@ page import="com.healthcare.model.User" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Book Appointment</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<h2>Book Appointment</h2>

<% String error = (String) request.getAttribute("error");
   String message = (String) request.getAttribute("message");
   if (error != null) { %>
    <p class="error"><%= error %></p>
<% } else if (message != null) { %>
    <p class="success"><%= message %></p>
<% } %>

<form action="bookAppointment" method="post" id="appointmentForm">
    <label>Doctor ID:</label>
    <input type="number" name="doctorId" required><br/>

    <label>Date:</label>
    <input type="date" name="date" required><br/>

    <label>Time Slot:</label>
    <select name="timeSlot" required>
        <option value="10:00-10:30">10:00-10:30</option>
        <option value="10:30-11:00">10:30-11:00</option>
        <option value="11:00-11:30">11:00-11:30</option>
    </select><br/>

    <label>Reason:</label>
    <textarea name="reason"></textarea><br/>

    <button type="submit">Book</button>
</form>

<script src="assets/js/validation.js"></script>
</body>
</html>
