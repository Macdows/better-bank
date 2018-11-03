package com.ynov.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Float amount;
    private String name;
    private String src_account;
    private String dest_account;
    private Date date;

    @ManyToOne
    private Account account;

    public Transaction(String name, Float amount, Date date, String src_account, String dest_account) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.dest_account = dest_account;
        this.src_account = src_account;
    }

    public Transaction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDest_account() {
        return dest_account;
    }

    public void setDest_account(String dest_account) {
        this.dest_account = dest_account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc_account() {
        return src_account;
    }

    public void setSrc_account(String src_account) {
        this.src_account = src_account;
    }
}
