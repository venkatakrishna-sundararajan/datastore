package com.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;


public class Account {

    String Id;
    String accountName;
    BigDecimal balance;

    public Account(){

    }
    public void setId(String id) {
        Id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getId() {
        return Id;
    }

    public String getAccountName() {
        return accountName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id='" + Id + '\'' +
                ", accountName='" + accountName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
