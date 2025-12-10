package com.healthcare.servlet;

import com.healthcare.dao.AppointmentDAO;
import com.healthcare.dao.AppointmentDAOImpl;
import com.healthcare.model.Appointment;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/appointments")
public class ListAppointmentsServlet extends HttpServlet {

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
            List<Appointment> list = appointmentDAO.getAppointmentsForPatient(currentUser.getId());
            request.setAttribute("appointments", list);
            request.getRequestDispatcher("appointments.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to load appointments.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
