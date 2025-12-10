<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register - Healthcare System</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<h2>Patient Registration</h2>

<% String error = (String) request.getAttribute("error");
   if (error != null) { %>
    <p class="error"><%= error %></p>
<% } %>

<form action="register" method="post" id="registerForm">
    <label>Name:</label>
    <input type="text" name="name" required><br/>

    <label>Email:</label>
    <input type="email" name="email" required><br/>

    <label>Phone:</label>
    <input type="text" name="phone"><br/>

    <label>Password:</label>
    <input type="password" name="password" required><br/>

    <label>Confirm Password:</label>
    <input type="password" name="confirmPassword" required><br/>

    <button type="submit">Register</button>
</form>

<script src="assets/js/validation.js"></script>
</body>
</html>
