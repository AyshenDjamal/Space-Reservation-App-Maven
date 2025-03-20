package org.example;
import java.util.HashMap;

public class ReservationService {
    public static HashMap<Integer, Reservation> reservations = new HashMap<>();


    public static void viewSpaces() {
        if (WorkSpaceService.coworkingSpaces.isEmpty()) {
            System.out.println("No coworking spaces are available.");
        } else {
            System.out.println("-------------List of Coworking Spaces----------");
            WorkSpaceService.coworkingSpaces.values().stream()
                    .filter(CoworkingSpace::getIsAvailable)
                    .forEach(System.out::println);
        }
    }


    public static boolean bookSpace(int resID, String name, String date, String start, String end) {
        CoworkingSpace space = WorkSpaceService.coworkingSpaces.get(resID);
        if (space != null && !reservations.containsKey(resID)) {
            ReservationService.reservations.put(resID, new Reservation(resID, name, date, start, end));
            space.setIsAvailable(false);
            WorkSpaceService.coworkingSpaces.put(resID, space);
            return true;
        }
        return false;
    }


    public static void myBookings() {
        System.out.println("------------Display My Bookings-------------");
        if (reservations.isEmpty()) {
            System.out.println("You don't have a booking. ");
        } else {
            reservations.values().forEach(System.out::println);
        }
    }


    public static boolean cancelBooking(int canID) {
        CoworkingSpace space = WorkSpaceService.coworkingSpaces.get(canID);
        if (space != null && reservations.containsKey(canID)) {
            reservations.remove(canID);

            space.setIsAvailable(true);
            WorkSpaceService.coworkingSpaces.put(canID, space);
            return true;
        }
        return false;
    }
}


