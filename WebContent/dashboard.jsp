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
    <title>Dashboard - Healthcare System</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<h2>Welcome, <%= user.getName() %>!</h2>
<p>Role: <%= user.getRole() %></p>

<ul>
    <li><a href="book_appointment.jsp">Book Appointment</a></li>
    <li><a href="appointments">My Appointments</a></li>
    <li><a href="logout">Logout</a></li>
</ul>
</body>
</html>
