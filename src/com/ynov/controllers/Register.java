package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Register extends HttpServlet {

    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
        logger.info("Register :: POST /register");

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String birth_date = req.getParameter("birth_date");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");


        User user = new User(firstname, lastname, email, username, password, birth_date, phone, address);

        User existingUser = UserManager.loadUserByUsernameAndPassword(username, password);

        if(firstname == null || lastname == null || username == null || password == null || email == null) {
            System.out.println("All fields are required.");
            req.setAttribute("errorMsg", "error_all_fields_required");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
            logger.error("Register :: POST /register :: All fields required");
        } else {
            if (existingUser != null) {
                req.setAttribute("errorMsg", "error_user_already_exists");
                dispatcher.forward(req, resp);
                logger.error("Register :: POST /register :: User already exists");
            } else {
                boolean b = UserManager.saveUser(user);
                if (b) {
                    resp.sendRedirect(req.getContextPath() + "/login.jsp");
                    logger.info("Register :: POST /register :: Success");
                } else {
                    req.setAttribute("errorMsg", "error_saving_user");
                    dispatcher.forward(req, resp);
                    logger.error("Register :: POST /register :: saveUser failed");
                }
            }
        }
    }
}