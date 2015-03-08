package model.dao;

import model.entity.Account;
import org.hibernate.Query;
import util.HibernateUtil;

public class AccountDAOImpl extends GenericDAOImpl<Account> {

    public Account getAccountByNumber(String accountNumber) {
        Query queryResult = HibernateUtil.getSession().createQuery("from Account account where account.accountNum= :number");
        queryResult.setString("number", accountNumber);
        Account currentAccount = (Account) queryResult.uniqueResult();
        return currentAccount;
    }
}
