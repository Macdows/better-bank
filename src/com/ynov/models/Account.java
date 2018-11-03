package com.ynov.models;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Float balance;
    private Float interest_rate;
    private Date created_at;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new LinkedHashSet<Transaction>();

    @ManyToOne
    private User user;

    public Account(Float balance, Float interest_rate, User user) {
        this.balance = balance;
        this.interest_rate = interest_rate;
        this.user = user;
        this.created_at = new Date();
        //transactions = transactions;
    }

    public Account(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountType getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountType account_type) {
        this.account_type = account_type;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = Float.sum(this.balance, balance);
    }

    public Float getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(Float interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
