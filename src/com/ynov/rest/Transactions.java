package com.ynov.rest;

import com.ynov.managers.AccountManager;
import com.ynov.managers.TransactionManager;
import com.ynov.models.Account;
import com.ynov.models.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/api/transaction")
public class Transactions extends HttpServlet {

    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("REST :: Transaction :: POST /api/transaction");

        String name = req.getParameter("name");
        Float amount = Float.parseFloat(req.getParameter("amount"));
        Integer srcAccountId = Integer.parseInt(req.getParameter("src_account"));
        Integer destAccountId = Integer.parseInt(req.getParameter("dest_account"));

        Account src_account = AccountManager.loadAccountById(srcAccountId);
        Account dest_account = AccountManager.loadAccountById(destAccountId);

        Transaction t = new Transaction(name, amount, new Date(), src_account, dest_account);
        TransactionManager.saveTransaction(t);
        AccountManager.updateBalance(src_account.getId(), -amount);
        AccountManager.updateBalance(dest_account.getId(), amount);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject("{ \"name\": \""+name+"\" }"));
        out.flush();
    }
}