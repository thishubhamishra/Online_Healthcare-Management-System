package com.healthcare.servlet;

import com.healthcare.dao.UserDAO;
import com.healthcare.dao.UserDAOImpl;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.login(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", user);

                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong during login.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
