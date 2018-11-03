package com.ynov.controllers;

import com.ynov.managers.AccountManager;
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

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.jsp");

        User user = (User) req.getSession().getAttribute("user");
        user.addAccount(AccountManager.loadAccountById(1));
        Set<Account> accounts = user.getAccounts();
        req.setAttribute("accounts", accounts);
        dispatcher.forward(req, resp);
    }

}
