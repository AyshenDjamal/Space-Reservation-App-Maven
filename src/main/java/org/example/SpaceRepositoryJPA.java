package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class SpaceRepositoryJPA {
    public static boolean insertSpace(CoworkingSpace space) {
        try (EntityManager em = HibernatePersistence.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(space);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    public static boolean deleteSpace(int spaceID){
        try(EntityManager em = HibernatePersistence.getEntityManager()){
            em.getTransaction().begin();

            CoworkingSpace space = em.find(CoworkingSpace.class, spaceID);
            em.remove(space);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }


    public static List<Reservation> getAllBookings(){
        EntityManager em = HibernatePersistence.getEntityManager();
        try{
            TypedQuery<Reservation> query = em.createQuery("SELECT r FROM Reservation r", Reservation.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }


    public static CoworkingSpace findSpaceID(int spaceID){
       try(EntityManager em = HibernatePersistence.getEntityManager()) {
           return em.find(CoworkingSpace.class, spaceID);
       }
    }
}
