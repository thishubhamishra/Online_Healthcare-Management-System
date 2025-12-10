<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Healthcare System - Login</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<h2>Login</h2>

<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<c:if test="${not empty message}">
    <p class="success">${message}</p>
</c:if>

<form action="login" method="post" id="loginForm">
    <label>Email:</label>
    <input type="email" name="email" required><br/>
    <label>Password:</label>
    <input type="password" name="password" required><br/>
    <button type="submit">Login</button>
</form>

<p>New user? <a href="register.jsp">Register here</a></p>

<script src="assets/js/validation.js"></script>
</body>
</html>
