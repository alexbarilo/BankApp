package model.dao;

import model.entity.Client;
import org.hibernate.Query;
import util.HibernateUtil;

public class ClientDAOImpl extends GenericDAOImpl<Client> {
    /*The method is used for ClientDAOImpl testing*/
    public Client getClientByName(String clientName) {
        Query query = HibernateUtil.getSession().createQuery("from Client client where client.firstName= :name");
        query.setString("name", clientName);
        Client client = (Client) query.uniqueResult();
        return client;
    }
}
