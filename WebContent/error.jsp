<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h2>Oops! Something went wrong.</h2>
<p><%= request.getAttribute("error") %></p>
<a href="dashboard">Go to Dashboard</a>
</body>
</html>
