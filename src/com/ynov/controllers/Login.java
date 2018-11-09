package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class Login extends HttpServlet{


    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher loginDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        logger.info("Login :: POST /login");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserManager.loadUserByUsernameAndPassword(username, password);

        if(user == null) {
            req.setAttribute("errorMsg", "error_invalid_credentials");
            loginDispatcher.forward(req, resp);
            logger.error("Login :: POST /login :: Invalid credentials");
        } else {
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = req.getSession(true);
            newSession.setMaxInactiveInterval(2*60);

            newSession.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/accounts");
            logger.info("Login :: POST /login :: Success");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}