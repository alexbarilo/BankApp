package model.dao;

import model.entity.Account;
import model.entity.Client;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import util.HibernateUtil;

import java.util.HashSet;
import java.util.List;

public class ClientDAOImplTest {

    static private Client client;
    static private ClientDAOImpl clientDAO;

    @BeforeClass
    public static void setup() {
        clientDAO = new ClientDAOImpl();
        client = new Client();

        client.setFirstName("AAA");
        client.setLastName("BBB");
        client.setAddress("ADDRESS");
        client.setCity("CITY");
        client.setPostalCode(00000);
        client.setSetOfAccounts(new HashSet<Account>());
    }

    @Test
    public void saveOrUpdateEntity() {
        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        Client retrievedClient = clientDAO.getClientByName(client.getFirstName());
        clientDAO.deleteEntity(retrievedClient);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();

        Assert.assertNotNull(retrievedClient);
        Assert.assertTrue(retrievedClient.getClientID() > 0);
        Assert.assertEquals(retrievedClient.getFirstName(), client.getFirstName());
        Assert.assertEquals(retrievedClient.getLastName(), client.getLastName());
        Assert.assertEquals(retrievedClient.getAddress(), client.getAddress());
        Assert.assertEquals(retrievedClient.getCity(), client.getCity());
        Assert.assertEquals(retrievedClient.getPostalCode(), client.getPostalCode());
        Assert.assertNotNull(retrievedClient.getSetOfAccounts());
    }

    @Test
    public void getListOfEntitiesTest() {
        List<Client> listOfClients = null;
        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        listOfClients = clientDAO.getAllEntities(Client.class);
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();

        Assert.assertNotNull(listOfClients);
        Assert.assertTrue(listOfClients.contains(client));
    }

    @Test
    public void getEntityByIdTest() {
        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        int clientId = client.getClientID();
        Assert.assertTrue(clientId > 0);
        Client retrievedClient = clientDAO.getEntityByID(Client.class, clientId);
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertNotNull(retrievedClient);
        Assert.assertTrue(retrievedClient.getClientID() > 0);
    }

    @Test
    public void getClientByNameTest() {
        HibernateUtil.beginTransaction();
        clientDAO.saveOrUpdateEntity(client);
        Client retrievedClient = clientDAO.getClientByName(client.getFirstName());
        clientDAO.deleteEntity(client);
        HibernateUtil.getSession().clear();
        HibernateUtil.commitTransaction();
        Assert.assertEquals(retrievedClient.getFirstName(), client.getFirstName());
    }

    @AfterClass
    public static void clearSession() {
        HibernateUtil.beginTransaction();
        clientDAO.deleteEntity(client);
        HibernateUtil.commitTransaction();
    }
}
