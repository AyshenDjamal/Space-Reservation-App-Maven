package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernatePersistence {

    private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("JpaUnit");

    public static EntityManager getEntityManager(){
        return em.createEntityManager();
    }

    public static void close(){
        em.close();
    }
}
