package model.service;

import junit.framework.Assert;
import model.dao.BankTransactionDAOImpl;
import model.dao.ClientDAOImpl;
import model.entity.Account;
import model.entity.BankTransaction;
import model.entity.Client;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.HibernateUtil;

import java.util.HashSet;

public class BankTransactionManagerImplTest {

    private static Client client;
    private static Account account;
    private static BankTransaction bankTransaction;

    private static ClientManagerImpl clientManager;
    private static ClientDAOImpl clientDAO;
    private static AccountManagerImpl accountManager;
    private static BankTransactionDAOImpl bankDAO;
    private static BankTransactionManagerImpl bankManager;

    @BeforeClass
    public static void setup() {

        client = new Client();
        account = new Account();
        bankTransaction = new BankTransaction();

        clientManager = new ClientManagerImpl();
        clientDAO = new ClientDAOImpl();
        accountManager = new AccountManagerImpl();
        bankManager = new BankTransactionManagerImpl();
        bankDAO = new BankTransactionDAOImpl();

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

        bankTransaction.setBenAccountNum("BANK_ACCOUNT");
        bankTransaction.setBankName("BANK_NAME");
        bankTransaction.setBenFirstName("FIRST_NAME");
        bankTransaction.setBenLastName("LAST_NAME");
        bankTransaction.setBenAmount(0.0f);
        bankTransaction.setDate("2001-01-01");

        clientManager.saveOrUpdateClient(client);
        account.setClientID(client);
        client.getSetOfAccounts().add(account);
        accountManager.saveAccount(account, client);
    }

    @Test
    public void saveBankTransactionTest() {
        bankManager.saveBankTransaction(bankTransaction, account);
        HibernateUtil.beginTransaction();
        BankTransaction retrievedBankTransaction = bankDAO.getEntityByID(BankTransaction.class, bankTransaction.getTransactionID());
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedBankTransaction);
        Assert.assertEquals("BANK_ACCOUNT", retrievedBankTransaction.getBenAccountNum());
    }

    @AfterClass
    public static void clearSession() {
        HibernateUtil.beginTransaction();
        clientDAO.deleteEntity(client);
        HibernateUtil.commitTransaction();
    }
}
