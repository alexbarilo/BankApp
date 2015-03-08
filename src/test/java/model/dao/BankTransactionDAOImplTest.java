package model.dao;

import model.entity.Account;
import model.entity.BankTransaction;
import model.entity.Client;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import util.HibernateUtil;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BankTransactionDAOImplTest {

    private static ClientDAOImpl clientDAO;
    private static AccountDAOImpl accountDAO;
    private static BankTransactionDAOImpl bankTransactionDAO;
    private static Client client;
    private static Account account;
    private static BankTransaction bankTransaction;

    @BeforeClass
    public static void setup() {

        clientDAO = new ClientDAOImpl();
        accountDAO = new AccountDAOImpl();
        bankTransactionDAO = new BankTransactionDAOImpl();
        client = new Client();
        account = new Account();
        bankTransaction = new BankTransaction();

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
        bankTransaction.setAccountNum(account);

        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        client.getSetOfAccounts().add(account);
        account.setClientID(client);
        accountDAO.saveOrUpdateEntity(account);
        HibernateUtil.commitTransaction();
    }

    @Test
    public void saveOrUpdateEntityTest() {
        HibernateUtil.beginTransaction();
        bankTransaction.setAccountNum(account);
        bankTransactionDAO.saveOrUpdateEntity(bankTransaction);
        BankTransaction retrievedTransaction = bankTransactionDAO
                .getEntityByID(BankTransaction.class, bankTransaction.getTransactionID());
        bankTransactionDAO.deleteEntity(bankTransaction);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        assertNotNull(retrievedTransaction);
        assertEquals("BANK_ACCOUNT", bankTransaction.getBenAccountNum());
    }


    @AfterClass
    public static void clearSession() {
        HibernateUtil.beginTransaction();
        bankTransactionDAO.deleteEntity(bankTransaction);
        clientDAO.deleteEntity(client);
        clientDAO.deleteEntity(client);
        HibernateUtil.commitTransaction();
    }

}
