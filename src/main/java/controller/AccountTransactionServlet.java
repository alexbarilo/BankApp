package controller;

import model.entity.Account;
import model.entity.Client;
import model.service.AccountManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountTransactionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = null;
        String requestReceiver = null;
        Client currentClient = null;
        Account currentAccount = null;

        HttpSession httpSession = req.getSession();

        String accountAction = req.getParameter("account-action");
        String currentAccountNum = req.getParameter("account-selection");

        AccountManagerImpl accountManager = new AccountManagerImpl();

        switch(accountAction) {
            case "Show account details" :
                currentAccount = accountManager.getAccount(currentAccountNum);
                httpSession.setAttribute("currentAccount", currentAccount);
                requestReceiver = "account_transact_details.jsp";
                break;
            case "Add account" :
                requestReceiver = "new_account.jsp";
                break;
            case "Delete account" :
                currentClient = (Client) httpSession.getAttribute("currentClient");
                currentClient = accountManager.deleteAccount(currentAccountNum, currentClient);
                httpSession.setAttribute("currentClient", currentClient);
                requestReceiver = "client_account_buttons.jsp";
                break;
            case "Update account" :
                currentAccount = accountManager.getAccount(currentAccountNum);
                req.setAttribute("currentAccount", currentAccount);
                requestReceiver = "update_account.jsp";
                break;
            case "Add transaction" :
                currentAccount = accountManager.getAccount(currentAccountNum);
                httpSession.setAttribute("currentAccount", currentAccount);
                requestReceiver = "new_transaction.jsp";
                break;
        }

        requestDispatcher = req.getRequestDispatcher(requestReceiver);
        requestDispatcher.forward(req, resp);
    }
}
