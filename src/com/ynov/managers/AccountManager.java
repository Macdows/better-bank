package com.ynov.managers;

import com.ynov.models.Account;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AccountManager extends BaseManager
{
    public static boolean saveAccount(Account account){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return em.find(Account.class, account.getId()).getId() == account.getId() ;
    }

    public static void deleteAccount(int accountId){
        EntityManager em = getEntityManager();
        Account account = em.find(Account.class, accountId);
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();
    }

    public static Account loadAccountById(int accountId){
        EntityManager em  = getEntityManager();
        Account account = em.find(Account.class, accountId);
        return account;
    }

    public static List<Object[]> loadAccountList(/*int userId*/){
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery ("SELECT * from account");
        query.getResultList().toArray();
        return query.getResultList();
    }

    public static void updateBalance(int accountId, float balance) {
        EntityManager em = getEntityManager();
        Account account = em.find(Account.class, accountId);
        float amount = account.getBalance() + balance;
        account.setBalance(amount);
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
    }

    /*public static void purgeTable() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Transaction").executeUpdate();
        em.createQuery("delete from Account").executeUpdate();
        // em.createQuery("delete from Client").executeUpdate();
        em.getTransaction().commit();
    }*/
}
