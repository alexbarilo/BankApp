package controller;

import model.entity.Account;
import model.entity.BankTransaction;
import model.entity.Client;
import model.service.AccountManagerImpl;
import model.service.BankTransactionManagerImpl;
import model.service.ClientManagerImpl;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class NewFormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /** Method gets data from new_account.jsp, new_client.jsp, update_client.jsp, new_transaction.jsp and
         * save it as relevant entity in DB.
        **/
        Client currentClient = null;
        Account currentAccount = null;
        BankTransaction currentBankTransaction = null;
        RequestDispatcher dispatcher = null;
        String requestReceiver = null;
        HttpSession httpSession = req.getSession();

        String formIdentifier = req.getParameter("jsp-identifier");

        ClientManagerImpl clientManager = new ClientManagerImpl();
        AccountManagerImpl accountManager = new AccountManagerImpl();
        BankTransactionManagerImpl bankTransactionManager = new BankTransactionManagerImpl();

        switch (formIdentifier) {
            case "client-form" :
                currentClient = clientManager.setClientProperties(req);
                /** If new Client-instance with the same first and second name already exists
                 * the catch-block redirects to error-page which describes the issue.
                **/
                try {
                    clientManager.saveOrUpdateClient(currentClient);
                } catch (HibernateException e) {
                    String message = "Sorry, the client with first and second name already exists";
                    String redirectionPage = "client_list.jsp";
                    req.setAttribute("message", message);
                    req.setAttribute("redirectionPage", redirectionPage);
                    requestReceiver = "error_page.jsp";
                    break;
                }

                List<Client> listOfClients = clientManager.getListOfClients();

                httpSession.setAttribute("listOfClients", listOfClients);
                requestReceiver = "client_list.jsp";
            break;

            case "account-form" :
                currentClient = (Client) httpSession.getAttribute("currentClient");

                currentAccount = accountManager.setAccountProperties(req, currentClient);
                currentClient = accountManager.saveAccount(currentAccount, currentClient);

                httpSession.setAttribute("currentClient", currentClient);
                requestReceiver = "client_account_buttons.jsp";
            break;

            case "update-account-form" :
                currentClient = (Client) httpSession.getAttribute("currentClient");
                currentAccount = accountManager.setAccountProperties(req, currentClient);
                currentClient = accountManager.updateAccount(currentAccount);

                httpSession.setAttribute("currentClient", currentClient);
                requestReceiver = "client_account_buttons.jsp";
            break;

            case "transaction-form" :
                currentAccount = (Account) httpSession.getAttribute("currentAccount");

                currentBankTransaction = bankTransactionManager.setTransactionProperties(req);
                currentAccount = bankTransactionManager.saveBankTransaction(currentBankTransaction, currentAccount);

                httpSession.setAttribute("currentAccount", currentAccount);
                requestReceiver = "account_transact_details.jsp";
            break;
        }
        dispatcher = req.getRequestDispatcher(requestReceiver);
        dispatcher.forward(req, resp);
    }
}
