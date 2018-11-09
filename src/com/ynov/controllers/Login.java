package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet{

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
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = req.getSession(true);
            newSession.setMaxInactiveInterval(2*60);

            newSession.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/accounts");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}