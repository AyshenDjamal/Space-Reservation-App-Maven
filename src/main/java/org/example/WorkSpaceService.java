package org.example;
import java.util.List;

public class WorkSpaceService {


    public static boolean addSpace(int id, String spaceType, double price, boolean isAvailable) {
        return SpaceRepositoryJPA.insertSpace(new CoworkingSpace(id, spaceType, price, isAvailable));
    }


    public static boolean removeSpace(int id) {
        return SpaceRepositoryJPA.deleteSpace(id);
    }


    public static void viewAllBookings() {
        List<Reservation> reservationList = SpaceRepositoryJPA.getAllBookings();
        if (reservationList.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("No reservations were found.\n");
        } else {
            System.out.println("-------------List Of Reservations-----------");
            reservationList.forEach(System.out::println);
        }
    }

}




