package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet{

    private static final long serialVersionUId = -3856737018663869874L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/login.jsp");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserManager.loadUserByUsernameAndPassword(username, password);

        if(user == null) {
            req.setAttribute("errorMsg", "error_invalid_credentials");
            loginDispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}