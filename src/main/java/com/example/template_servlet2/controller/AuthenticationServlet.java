package com.example.template_servlet2.controller;

import com.example.template_servlet2.entities.Employee;
import com.example.template_servlet2.repositories.EmployeeRepository;
import com.example.template_servlet2.utils.ValidationArguments;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", value = "")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email.trim().isBlank() || email == null){
            request.setAttribute("loginError", "You must enter the email to proceed!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        Employee employee = new EmployeeRepository().findByEmail(email);

        if (employee == null) {
            request.setAttribute("loginError", "This email is not exist!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        boolean isValidPassword = ValidationArguments.validationPasswordArgon(employee, password);
        if (!isValidPassword){
            request.setAttribute("loginError", "Invalid Password or Email");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", employee);
            response.sendRedirect(request.getContextPath());
        }
    }
}
