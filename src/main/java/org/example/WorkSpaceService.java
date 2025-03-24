package org.example;
import java.util.List;

public class WorkSpaceService {


    public static boolean addSpace(int id, String spaceType, double price, boolean isAvailable) {
        CoworkingSpace space = new CoworkingSpace(id, spaceType, price, isAvailable);
        return SpaceRepository.addSpace(space);
    }


    public static boolean removeSpace(int id) {
        return SpaceRepository.removeSpace(id);
    }


    public static void viewAllBookings() {
        List<Reservation> reservationList = SpaceRepository.getAllBookings();
        if (reservationList.isEmpty()) {
            System.out.println("------------------------------");
            System.out.println("No reservations were found.\n");
        } else {
            System.out.println("-------------List Of Reservations-----------");
            reservationList.forEach(System.out::println);
        }
    }

}




