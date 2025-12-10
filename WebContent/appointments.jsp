<%@ page import="java.util.List" %>
<%@ page import="com.healthcare.model.Appointment" %>
<%@ page import="com.healthcare.model.User" %>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
%>
<html>
<head>
    <title>My Appointments</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
<h2>My Appointments</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Doctor ID</th>
        <th>Date</th>
        <th>Time Slot</th>
        <th>Reason</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <% if (appointments != null) {
           for (Appointment a : appointments) { %>
        <tr>
            <td><%= a.getId() %></td>
            <td><%= a.getDoctorId() %></td>
            <td><%= a.getAppointmentDate() %></td>
            <td><%= a.getTimeSlot() %></td>
            <td><%= a.getReason() %></td>
            <td><%= a.getStatus() %></td>
            <td>
                <% if ("BOOKED".equals(a.getStatus())) { %>
                    <a href="cancelAppointment?id=<%= a.getId() %>">Cancel</a>
                <% } %>
            </td>
        </tr>
    <%   }
       } %>
</table>

<a href="dashboard">Back to Dashboard</a>
</body>
</html>
