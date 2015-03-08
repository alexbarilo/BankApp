package model.service;

import model.dao.AccountDAOImpl;
import model.dao.ClientDAOImpl;
import model.entity.Account;
import model.entity.BankTransaction;
import model.entity.Client;
import org.hibernate.Hibernate;
import util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AccountManagerImpl {

    AccountDAOImpl accountDAO = new AccountDAOImpl();

    public Account getAccount(String accountNumber) {
        Account currentAccount = null;
        try {
            HibernateUtil.beginTransaction();
            currentAccount = accountDAO.getAccountByNumber(accountNumber);
            Hibernate.initialize(currentAccount.getSetOfTransactions());
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return currentAccount;
    }

    public Client saveAccount(Account account, Client client) {
        Client currentClient = null;
        ClientDAOImpl clientDAO = new ClientDAOImpl();
        try {
            HibernateUtil.beginTransaction();
            currentClient = clientDAO.getEntityByID(Client.class, client.getClientID());
            account.setClientID(currentClient);
            currentClient.getSetOfAccounts().add(account);
            clientDAO.saveOrUpdateEntity(currentClient);
            currentClient = clientDAO.getEntityByID(Client.class, currentClient.getClientID());
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return currentClient;
    }

    public Client updateAccount(Account account) {
        Client currentClient = null;
        try {
            HibernateUtil.beginTransaction();
            accountDAO.saveOrUpdateEntity(account);
            currentClient = account.getClientID();
            Hibernate.initialize(currentClient.getSetOfAccounts());
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return currentClient;
    }

    public Client deleteAccount(String accountNumber, Client client) {

        Account currentAccount = null;
        Client currentClient = null;

        ClientDAOImpl clientDAO = new ClientDAOImpl();

        try {
            HibernateUtil.beginTransaction();
            currentAccount = accountDAO.getAccountByNumber(accountNumber);
            currentClient = clientDAO.getEntityByID(Client.class, client.getClientID());
            currentClient.getSetOfAccounts().remove(currentAccount);
            clientDAO.saveOrUpdateEntity(currentClient);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return currentClient;
    }

    /** Check if it will be of use for further development **/
    public List<Account> getListOfAccounts() {
        List<Account> listOfAccounts = new ArrayList<Account>();
        try {
            HibernateUtil.beginTransaction();
            listOfAccounts = accountDAO.getAllEntities(Account.class);
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return listOfAccounts;
    }

    public Account setAccountProperties(HttpServletRequest req, Client currentClient) {
        Account currentAccount = new Account();

        String clientID = req.getParameter("client-id");
        String accountNum = req.getParameter("account-number");
        String accountAmount = req.getParameter("account-amount");
        String accountCurrency = req.getParameter("account-currency");
        String issueDate = req.getParameter("account-date");

        float accountAmountFloat = Float.parseFloat(accountAmount);

        if (clientID.length() != 0) {
            currentAccount.setClientID(currentClient);
        }
        currentAccount.setAccountNum(accountNum);
        currentAccount.setAmount(accountAmountFloat);
        currentAccount.setCurrency(accountCurrency);
        currentAccount.setDate(issueDate);
        currentAccount.setSetOfTransactions(new HashSet<BankTransaction>());

        return currentAccount;
    }
}
