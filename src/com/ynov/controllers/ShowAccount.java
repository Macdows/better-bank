package com.ynov.controllers;

import com.ynov.managers.AccountManager;
import com.ynov.models.Account;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account")
public class ShowAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/account.jsp");

        Integer accountId = Integer.parseInt(req.getParameter("id"));

        Account account = AccountManager.loadAccountById(accountId);
        if (account != null) {
            req.setAttribute("account", account);
            req.setAttribute("transactions", account.getTransactions());
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/accounts");
        }
    }
}
