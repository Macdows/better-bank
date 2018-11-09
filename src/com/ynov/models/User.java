package com.ynov.models;

import com.ynov.managers.UserManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String birth_date;
    private String phone;
    private String address;

    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private Set<Account> accounts = new LinkedHashSet<Account>();

    public User(String firstname, String lastname, String email, String username, String password, String birth_date, String phone, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birth_date = birth_date;
        this.phone = phone;
        this.address = address;
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User() {

    }

    private boolean testStr (String str, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.lookingAt();
    }

    public void changePassword (String newPassword) {
        if (newPassword.length() < 8) {
            throw new RuntimeException("Password too short.");
        } else if (!this.testStr(newPassword, ".*\\d.*")) {
            throw new RuntimeException("Password must have a number.");
        } else if (!this.testStr(newPassword, ".*[A-Z].*")) {
            throw new RuntimeException("Password must have a capital letter.");
        } else if (!this.testStr(newPassword, ".*[`~!@#$%^&*()\\\\-_=+\\\\\\\\|\\\\[{\\\\]};:'\\\",<.>/?].*")) {
             throw new RuntimeException("Password must have a special character.");
        //} else if (this.testStr(newPassword, ".*[p{InCombiningDiacriticalMarks}].*")) {
        //    throw new RuntimeException("Password must not have characters with accents.");
        } else {
            this.password = newPassword;
        }
    }

    public void addAccount(Account account){
        account.setUser(this);
        accounts.add(account);
    }

    public Set<Account> getAccounts() { return accounts; }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth_date() { return birth_date; }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Firstname: " + this.firstname + "\nLastname: " + this.lastname + "\nEmail: " + this.email + "\nUsername: " + this.username + "\nPassword: " + this.password + "\nBirth date: " + this.birth_date +  "\nPhone number: " + this.phone + "\nAddress: " + this.address;
    }
}
