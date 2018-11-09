package com.ynov.managers;

import com.ynov.models.Transaction;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;

public class TransactionManager extends BaseManager {
    public static boolean saveTransaction(Transaction transaction){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(transaction);
        em.getTransaction().commit();
        return true;
    }
}
