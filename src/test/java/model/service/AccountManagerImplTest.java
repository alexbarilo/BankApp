package model.service;

import model.dao.AccountDAOImpl;
import model.entity.Account;
import model.entity.BankTransaction;
import model.entity.Client;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import util.HibernateUtil;


import java.util.HashSet;
import java.util.List;

public class AccountManagerImplTest {

    private static Client client;
    private static Account account;
    private static ClientManagerImpl clientManager;
    private static AccountManagerImpl accountManager;
    private static AccountDAOImpl accountDAO;

    @BeforeClass
    public static void setup() {

        client = new Client();
        account = new Account();
        clientManager = new ClientManagerImpl();
        accountManager = new AccountManagerImpl();
        accountDAO = new AccountDAOImpl();

        client.setFirstName("AAA");
        client.setLastName("BBB");
        client.setAddress("ADDRESS");
        client.setCity("CITY");
        client.setPostalCode(00000);
        client.setSetOfAccounts(new HashSet<Account>());

        account.setAccountNum("ACCOUNT_NUM");
        account.setAmount(0.0f);
        account.setCurrency("CUR");
        account.setDate("2001-01-01");
        account.setSetOfTransactions(new HashSet<BankTransaction>());

        clientManager.saveOrUpdateClient(client);
    }

    @Test
    public void saveAccountTest() {
        accountManager.saveAccount(account, client);
        HibernateUtil.beginTransaction();
        Account retrievedAccount = accountDAO.getAccountByNumber(account.getAccountNum());
        HibernateUtil.commitTransaction();
        HibernateUtil.beginTransaction();
        accountDAO.deleteEntity(account);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedAccount);
        Assert.assertEquals("ACCOUNT_NUM", retrievedAccount.getAccountNum());
    }

    @Test
    public void getAccountTest() {
        accountManager.saveAccount(account, client);
        Account retrievedAccount = accountManager.getAccount(account.getAccountNum());
        HibernateUtil.beginTransaction();
        accountDAO.deleteEntity(account);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedAccount);
        Assert.assertEquals("ACCOUNT_NUM", retrievedAccount.getAccountNum());
    }

    @Test
    public void updateAccountTest() {
        accountManager.saveAccount(account, client);
        account.setCurrency("RUC");
        accountManager.updateAccount(account);
        Account retrievedAccount = accountManager.getAccount(account.getAccountNum());
        HibernateUtil.beginTransaction();
        accountDAO.deleteEntity(account);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedAccount);
        Assert.assertEquals("RUC", retrievedAccount.getCurrency());
    }

    @Test
    public void getListOfAccounts() {
        accountManager.saveAccount(account, client);
        List<Account> listOfAccounts = accountManager.getListOfAccounts();
        HibernateUtil.beginTransaction();
        accountDAO.deleteEntity(account);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(listOfAccounts);
        Assert.assertTrue(listOfAccounts.contains(account));
    }

    @AfterClass
    public static void clearSession() {
        clientManager.deleteClient(Integer.toString(client.getClientID()));
    }
}
