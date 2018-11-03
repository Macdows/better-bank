package com.ynov.managers;

import com.ynov.models.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserManager extends BaseManager
{
    public static boolean saveUser(User user){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return em.find(User.class, user.getId()).getId() == user.getId() ;
    }

    public static void deleteUser(User User){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(User);
        em.getTransaction().commit();
    }
    public static User loadUserById(int userId){
        EntityManager em  = getEntityManager();
        User user = em.find(User.class, userId);
        return user;
    }

    public static User loadUserByUsernameAndPassword(String username,String password){
        EntityManager em = getEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username='"+username+"' and u.password='"+password+"'", User.class);
        User User = query.getResultList().stream().findFirst().orElse(null);
        return User;
    }

    public static void purgeTable() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Transaction").executeUpdate();
        em.createQuery("delete from Account").executeUpdate();
        // em.createQuery("delete from Client").executeUpdate();
        em.getTransaction().commit();
    }
}
