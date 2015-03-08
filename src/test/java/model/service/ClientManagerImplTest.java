package model.service;

import model.dao.ClientDAOImpl;
import model.entity.Account;
import model.entity.Client;

import java.util.HashSet;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import util.HibernateUtil;

public class ClientManagerImplTest {

    private static Client client;
    private static ClientManagerImpl clientManager;
    private static ClientDAOImpl clientDAO;


    @BeforeClass
    public static void setup() {
        client = new Client();
        client.setFirstName("AAA");
        client.setLastName("BBB");
        client.setAddress("ADDRESS");
        client.setCity("CITY");
        client.setPostalCode(00000);
        client.setSetOfAccounts(new HashSet<Account>());

        clientManager = new ClientManagerImpl();
        clientDAO = new ClientDAOImpl();
    }

    /*
    * Two different transactions are used to avoid org.hibernate.NonUniqueObjectException
    * I assume the issue is that two objects with the same identifier are referred to the same session. Attempt to delete
    * the object fails because the ORM can't define the one to delete*/
    @Test
    public void saveOrUpdateClient() {
        clientManager.saveOrUpdateClient(client);
        HibernateUtil.beginTransaction();
        Client retrievedClient = clientDAO.getEntityByID(Client.class, client.getClientID());
        HibernateUtil.commitTransaction();
        HibernateUtil.beginTransaction();
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedClient);
        Assert.assertEquals("AAA", retrievedClient.getFirstName());
    }

    @Test
    public void getClientTest() {
        clientManager.saveOrUpdateClient(client);
        Client retrievedClient = clientManager.getClient(Integer.toString(client.getClientID()));
        HibernateUtil.beginTransaction();
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedClient);
        Assert.assertEquals("AAA", retrievedClient.getFirstName());
    }

    @Test
    public void getListOfClientsTest() {
        clientManager.saveOrUpdateClient(client);
        List<Client> listOfClients = clientManager.getListOfClients();
        HibernateUtil.beginTransaction();
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(listOfClients);
        Assert.assertTrue(listOfClients.contains(client));
    }

    @AfterClass
    public static void clearSession() {
        clientManager.deleteClient(Integer.toString(client.getClientID()));
    }

}
