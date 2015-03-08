package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.sql.Connection;

public class HibernateUtil {
    private static Session session;
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static ServiceRegistryBuilder registryBuilder;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            registryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
            serviceRegistry = registryBuilder.buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static Session getSession() {
        session = getSessionFactory().getCurrentSession();
        return session;
    }

    public static Session beginTransaction() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        return session;
    }

    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public static Connection closeSession() {
        Connection connection = getSession().close();
        return connection;
    }
}
