package model.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    private String accountNum;
    private float amount;
    private String currency;
    private String date;
    private Set<BankTransaction> setOfTransactions;
    private Client clientID;

    @Id
    @Column(name = "account_num")
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    @Column(name = "amount")
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @OneToMany(mappedBy = "accountNum", orphanRemoval = true)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    public Set<BankTransaction> getSetOfTransactions() {
        return setOfTransactions;
    }

    public void setSetOfTransactions(Set<BankTransaction> setOfTransactions) {
        this.setOfTransactions = setOfTransactions;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    public Client getClientID() {
        return clientID;
    }

    public void setClientID(Client clientID) {
        this.clientID = clientID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!accountNum.equals(account.accountNum)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return accountNum.hashCode();
    }
}
