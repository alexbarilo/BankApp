package util;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

public class HibernateUtilTest {

    static private Session session;

    @BeforeClass
    public static void setup() {
        session = HibernateUtil.getSession();
    }

    @Test
    public void getSessionTest() {
        Assert.assertNotNull(session);
    }

    @Test
    public void beginTransactionTest() {
        session = HibernateUtil.beginTransaction();
        Assert.assertNotNull(session);
        Assert.assertTrue(session instanceof Session);
    }

    @Test
    public void closeSession() {
        Connection connection = HibernateUtil.closeSession();
        Assert.assertNull(connection);
    }

}
