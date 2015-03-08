package model.service;

import model.dao.AccountDAOImpl;
import model.entity.Account;
import model.entity.BankTransaction;
import util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;

public class BankTransactionManagerImpl {

    AccountDAOImpl accountDAO = new AccountDAOImpl();

    public Account saveBankTransaction(BankTransaction bankTransaction, Account account){
        Account currentAccount = null;
        try {
            HibernateUtil.beginTransaction();
            bankTransaction.setAccountNum(account);
            account.getSetOfTransactions().add(bankTransaction);
            accountDAO.saveOrUpdateEntity(account);
            currentAccount = accountDAO.getAccountByNumber(account.getAccountNum());
            HibernateUtil.commitTransaction();
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return currentAccount;
    }

    public BankTransaction setTransactionProperties(HttpServletRequest req) {

        BankTransaction bankTransaction = new BankTransaction();

        String beneficiaryBankName = req.getParameter("ben-bank-name");
        String beneficiaryFirstName = req.getParameter("ben-first-name");
        String beneficiaryLastName = req.getParameter("ben-last-name");
        String beneficiaryAccountNum = req.getParameter("ben-account-num");
        String amountToRemit = req.getParameter("remit-amount");
        String transactionDate = req.getParameter("transaction-date");

        float amountToRemitFloat = Float.parseFloat(amountToRemit);

        bankTransaction.setBankName(beneficiaryBankName);
        bankTransaction.setBenFirstName(beneficiaryFirstName);
        bankTransaction.setBenLastName(beneficiaryLastName);
        bankTransaction.setBenAccountNum(beneficiaryAccountNum);
        bankTransaction.setBenAmount(amountToRemitFloat);
        bankTransaction.setDate(transactionDate);

        return bankTransaction;
    }
}