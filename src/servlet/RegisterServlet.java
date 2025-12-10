package com.healthcare.servlet;

import com.healthcare.dao.UserDAO;
import com.healthcare.dao.UserDAOImpl;
import com.healthcare.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");
        String role = "PATIENT";  // default patient

        // basic server-side validation
        if (name == null || name.trim().isEmpty() ||
            email == null || !email.contains("@") ||
            password == null || password.length() < 6 ||
            !password.equals(confirm)) {

            request.setAttribute("error", "Invalid input. Please check your details.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User(name, email, password, role, phone);

        try {
            boolean registered = userDAO.registerUser(user);
            if (registered) {
                request.setAttribute("message", "Registration successful. Please login.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Unable to register. Email might already exist.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
