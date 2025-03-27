package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class ReservationRepositoryJPA {

    public static List<CoworkingSpace>getAvailableSpaces(){
        EntityManager em = HibernatePersistence.getEntityManager();
        try{
            return em.createQuery("SELECT s FROM CoworkingSpace s", CoworkingSpace.class)
            .getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }



    public static boolean insertReservation(Reservation reservation){
        try(EntityManager em = HibernatePersistence.getEntityManager()){
            em.getTransaction().begin();
            reservation.getSpace().setIsAvailable(false);
            em.merge(reservation.getSpace());
            em.persist(reservation);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error: "+ e.getMessage());
            return false;
        }
    }



    public static List<Reservation> getMyBooking(int bookingID){
        EntityManager em = HibernatePersistence.getEntityManager();
        try{
            return em.createQuery("SELECT r FROM Reservation r WHERE r.bookingID = :id", Reservation.class)
                    .setParameter("id", bookingID)
            .getResultList();
        } catch (PersistenceException e) {
            System.out.println("Error: " + e.getMessage());
            return List.of();
        }
    }



    public static boolean deleteBooking(int bookingID){
        try(EntityManager em = HibernatePersistence.getEntityManager()){
            Reservation reservation = em.find(Reservation.class,bookingID);
            em.getTransaction().begin();
            reservation.getSpace().setIsAvailable(true);
            em.merge(reservation.getSpace());
            em.remove(reservation);
            em.getTransaction().commit();
            return true;
        }catch (PersistenceException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
