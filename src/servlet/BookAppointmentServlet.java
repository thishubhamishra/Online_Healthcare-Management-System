package com.healthcare.servlet;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.AppointmentDAOImpl;
import com.healthcare.model.Appointment;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/bookAppointment")
public class BookAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String timeSlot = request.getParameter("timeSlot");
            String reason = request.getParameter("reason");

            if (date.isBefore(LocalDate.now())) {
                request.setAttribute("error", "Date cannot be in the past.");
                request.getRequestDispatcher("book_appointment.jsp").forward(request, response);
                return;
            }

            Appointment appt = new Appointment(
                    currentUser.getId(),
                    doctorId,
                    date,
                    timeSlot,
                    reason
            );

            boolean booked = appointmentDAO.bookAppointment(appt);
            if (booked) {
                request.setAttribute("message", "Appointment booked successfully!");
            } else {
                request.setAttribute("error", "Time slot not available. Please choose another slot.");
            }

            request.getRequestDispatcher("book_appointment.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while booking appointment.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
