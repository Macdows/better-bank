package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile.jsp");

        String password = req.getParameter("password");

        User u = (User) req.getSession().getAttribute("user");
        User user = UserManager.loadUserById(u.getId());


        if(user != null && password != null) {
            try {
                UserManager.updatePassword(user.getId(), password);
            } catch(RuntimeException e) {
                req.setAttribute("message", e.getMessage());
                dispatcher.forward(req, resp);
            }
            req.setAttribute("message", "Le mot de passe a été mis à jour.");
            dispatcher.forward(req, resp);
        }
    }
}


