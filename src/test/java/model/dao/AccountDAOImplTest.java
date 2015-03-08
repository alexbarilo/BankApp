package model.dao;

import static org.junit.Assert.*;

import model.entity.BankTransaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import model.entity.Account;
import model.entity.Client;
import org.junit.Test;
import util.HibernateUtil;

import java.util.HashSet;
import java.util.List;

public class AccountDAOImplTest {

    private static AccountDAOImpl accountDAO;
    private static ClientDAOImpl clientDAO;
    private static Account account;
    private static Client client;

    @BeforeClass
    public static void setup() {
        clientDAO = new ClientDAOImpl();
        accountDAO = new AccountDAOImpl();
        client = new Client();
        account = new Account();

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
        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        HibernateUtil.commitTransaction();
    }

    @Test
    public void saveOrUpdateEntityTest() {
        HibernateUtil.beginTransaction();
        client.getSetOfAccounts().add(account);
        account.setClientID(client);
        accountDAO.saveOrUpdateEntity(account);
        Account retrievedAccount = accountDAO.getAccountByNumber(account.getAccountNum());
        accountDAO.deleteEntity(account);
        HibernateUtil.commitTransaction();
        assertNotNull(retrievedAccount);
        assertEquals("ACCOUNT_NUM", retrievedAccount.getAccountNum());
    }

    @Test
    public void getAccountByNumberTest() {
        HibernateUtil.beginTransaction();
        client.getSetOfAccounts().add(account);
        account.setClientID(client);
        accountDAO.saveOrUpdateEntity(account);
        Account retrievedAccount = accountDAO.getAccountByNumber(account.getAccountNum());
        //accountDAO.deleteEntity(account);
        HibernateUtil.commitTransaction();
        assertEquals("ACCOUNT_NUM", retrievedAccount.getAccountNum());
    }

    @Test
    public void getAllEntitiesTest() {
        HibernateUtil.beginTransaction();
        client.getSetOfAccounts().add(account);
        account.setClientID(client);
        accountDAO.saveOrUpdateEntity(account);
        List<Account> listOfAccounts = accountDAO.getAllEntities(Account.class);
        HibernateUtil.commitTransaction();
        assertNotNull(listOfAccounts);
        assertTrue(listOfAccounts.contains(account));
    }

    @AfterClass
    public static void clearSession() {
        HibernateUtil.beginTransaction();
        //accountDAO.deleteEntity(account);
        clientDAO.deleteEntity(client);
        HibernateUtil.commitTransaction();
    }
}
