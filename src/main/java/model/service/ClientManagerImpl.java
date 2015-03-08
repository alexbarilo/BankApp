package model.service;

import model.dao.ClientDAOImpl;
import model.entity.Account;
import model.entity.Client;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ClientManagerImpl {

    ClientDAOImpl clientDAO = new ClientDAOImpl();

    public void saveOrUpdateClient(Client client) {
        try {
            HibernateUtil.beginTransaction();
            clientDAO.saveOrUpdateEntity(client);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }

    }

    public void deleteClient(String clientID) {
        try {
            HibernateUtil.beginTransaction();
            Client entityToDelete = clientDAO.getEntityByID(Client.class, Integer.parseInt(clientID));
            clientDAO.deleteEntity(entityToDelete);
            HibernateUtil.commitTransaction();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
    }


    public Client getClient(String clientID) {
        Client currentClient = null;
        try {
            HibernateUtil.beginTransaction();
            currentClient = clientDAO.getEntityByID(Client.class, Integer.parseInt(clientID));
            Hibernate.initialize(currentClient.getSetOfAccounts());
            HibernateUtil.commitTransaction();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
        return currentClient;
    }

    public List<Client> getListOfClients() {
        List<Client> listOfClients = new ArrayList<Client>();
        try {
            HibernateUtil.beginTransaction();
            listOfClients = clientDAO.getAllEntities(Client.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            e.printStackTrace();
            HibernateUtil.rollbackTransaction();
        }
        return listOfClients;
    }

    public Client setClientProperties(HttpServletRequest req) {

        Client currentClient = new Client();

        String clientID = req.getParameter("clientID");
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String postalCode = req.getParameter("postal-code");

        if (clientID.length() != 0){
            currentClient.setClientID(Integer.parseInt(clientID));
        }

        currentClient.setFirstName(firstName);
        currentClient.setLastName(lastName);
        currentClient.setAddress(address);
        currentClient.setCity(city);
        currentClient.setPostalCode(Integer.parseInt(postalCode));
        currentClient.setSetOfAccounts(new HashSet<Account>());

        return currentClient;
    }
}
