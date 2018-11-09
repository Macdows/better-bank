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

    private String label;
    private Float balance;
    private Float interest_rate;
    private Date created_at;

    @OneToMany(mappedBy="src_account", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new LinkedHashSet<Transaction>();

    @OneToMany(mappedBy="dest_account", cascade = CascadeType.ALL)
    private Set<Transaction> credited_by = new LinkedHashSet<Transaction>();

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    public Account(String label, Float balance, Float interest_rate, User user) {
        this.label = label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) { this.balance = balance; }

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

    public Set<Transaction> getCredited_by() {
        return credited_by;
    }

}
