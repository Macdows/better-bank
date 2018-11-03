package com.ynov.controllers;

import com.ynov.managers.AccountManager;
import com.ynov.models.Account;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/account/balance")
public class Balance extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("id");
        String balance = req.getParameter("balance");

        Account account  = AccountManager.loadAccountById(Integer.parseInt(accountId));
        account.setBalance(Float.parseFloat(balance));

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject("{ \"accountId\": \""+accountId+"\", \"balance\": \""+account.getBalance()+"\" }"));
        out.flush();
    }
}