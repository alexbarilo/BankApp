package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class BankTransaction {

    private int transactionID;
    private String bankName;
    private String benFirstName;
    private String benLastName;
    private String benAccountNum;
    private float benAmount;
    private String date;
    private Account accountNum;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "ben_first_name")
    public String getBenFirstName() {
        return benFirstName;
    }

    public void setBenFirstName(String benFirstName) {
        this.benFirstName = benFirstName;
    }

    @Column(name = "ben_last_name")
    public String getBenLastName() {
        return benLastName;
    }

    public void setBenLastName(String benLastName) {
        this.benLastName = benLastName;
    }

    @Column(name = "ben_account_num")
    public String getBenAccountNum() {
        return benAccountNum;
    }

    public void setBenAccountNum(String benAccountNum) {
        this.benAccountNum = benAccountNum;
    }

    @Column(name = "ben_amount")
    public float getBenAmount() {
        return benAmount;
    }

    public void setBenAmount(float benAmount) {
        this.benAmount = benAmount;
    }

    @Column(name = "transaction_date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_num")
    public Account getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Account accountNum) {
        this.accountNum = accountNum;
    }
}
