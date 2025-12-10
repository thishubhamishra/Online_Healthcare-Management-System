package com.healthcare.servlet;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.AppointmentDAOImpl;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cancelAppointment")
public class CancelAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() {
        appointmentDAO = new AppointmentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean canceled = appointmentDAO.cancelAppointment(id, currentUser.getId());
            if (canceled) {
                request.setAttribute("message", "Appointment cancelled.");
            } else {
                request.setAttribute("error", "Unable to cancel appointment.");
            }
            request.getRequestDispatcher("appointments").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong while cancelling appointment.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
