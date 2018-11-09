package com.ynov.controllers;

import com.ynov.managers.UserManager;
import com.ynov.models.Account;
import com.ynov.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/accounts")
public class AccountList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account-list.jsp");

        User u = (User) req.getSession().getAttribute("user");
        User user = UserManager.loadUserById(u.getId());
        if (user != null) {
            Set<Account> accounts = user.getAccounts();
            req.setAttribute("accounts", accounts);
            req.setAttribute("userId", user.getId());
        }
        dispatcher.forward(req, resp);
    }

}
