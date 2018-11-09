package com.ynov.rest;

import com.ynov.managers.AccountManager;
import com.ynov.managers.UserManager;
import com.ynov.models.Account;
import com.ynov.models.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/account")
public class Accounts extends HttpServlet {

    Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("REST :: Accounts :: GET /api/account");

        //User currentUser = (User) req.getSession().getAttribute("user");
        ArrayList<JSONObject> finalJSON = new ArrayList<>();
        List<Object[]> accountList;
        accountList = AccountManager.loadAccountList(/*currentUser.getId()*/);
        for (Object[] account : accountList) {
            JSONObject json = new JSONObject();
            json.put("id", account[0]);
            json.put("type", account[1]);
            json.put("balance", account[2]);
            json.put("created_at", account[3]);
            json.put("interest_rate", account[4]);
            json.put("label", account[5]);
            json.put("userId", account[6]);
            finalJSON.add(json);
        }
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(finalJSON);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("REST :: Accounts :: POST /api/account");

        Float balance = Float.parseFloat(req.getParameter("balance"));
        Float interest_rate = Float.parseFloat(req.getParameter("interest_rate"));
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        String label = req.getParameter("label");

        User user = UserManager.loadUserById(userId);

        Account account = new Account(label, balance, interest_rate, user);
        AccountManager.saveAccount(account);
        user.addAccount(account);
        UserManager.updateUser(user.getId());

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(new JSONObject("{ \"account\": \""+account+"\" }"));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("REST :: Accounts :: DELETE /api/account");

        Integer id = Integer.parseInt(req.getParameter("id"));

        AccountManager.deleteAccount(id);

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print(true);
        out.flush();
    }


}
